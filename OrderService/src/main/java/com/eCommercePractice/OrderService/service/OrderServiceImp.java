package com.eCommercePractice.OrderService.service;

import com.eCommercePractice.OrderService.entity.Order;
import com.eCommercePractice.OrderService.exception.OrderServiceCustomException;
import com.eCommercePractice.OrderService.external.client.PaymentService;
import com.eCommercePractice.OrderService.external.client.ProductService;
import com.eCommercePractice.OrderService.external.modal.PaymentRequest;
import com.eCommercePractice.OrderService.modal.OrderRequest;
import com.eCommercePractice.OrderService.modal.OrderResponse;
import com.eCommercePractice.OrderService.modal.PaymentDetails;
import com.eCommercePractice.OrderService.modal.ProductDetails;
import com.eCommercePractice.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderResponse getOrderDetails(long id) {
        log.info("Get Order Details for Order Id : {}",id);
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderServiceCustomException("Order Detail Not Found","ERR_ORD_1",404));
        ProductDetails productDetails;
        PaymentDetails paymentDetails;
        try{
            productDetails = restTemplate.getForObject("http://Product-service/product/"+order.getProductId(),ProductDetails.class);
        }
        catch(Exception e) {
            throw new OrderServiceCustomException("Internal Server Error","ERR_INT_1",500);
        }
        try{
            paymentDetails = restTemplate.getForObject("http://Payment-service/payment/"+order.getOrderId(),PaymentDetails.class);
        }
        catch(Exception e) {
            throw new OrderServiceCustomException("Internal Server Error","ERR_INT_1",500);
        }
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getOrderId())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
        return orderResponse;
    }

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("placing Order Service layer"+orderRequest.toString());
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
         Order order = Order
                .builder()
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .build();
        orderRepository.save(order);

        log.info("Calling Payment Service to create payment transaction");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment Done Successfuly");
            order.setOrderStatus("PLACED");
            orderRepository.save(order);
        }
        catch (Exception e) {
            log.error("Error Occured in payment Service");
            order.setOrderStatus("PAYMENT_FAILED");
            orderRepository.save(order);
        }

        log.info("Order placed Successfully .......");

        return order.getOrderId();
    }
}
