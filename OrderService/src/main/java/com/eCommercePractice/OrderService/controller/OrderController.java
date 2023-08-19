package com.eCommercePractice.OrderService.controller;

import com.eCommercePractice.OrderService.modal.OrderRequest;
import com.eCommercePractice.OrderService.modal.OrderResponse;
import com.eCommercePractice.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") long id) {
        OrderResponse orderResponse = orderService.getOrderDetails(id);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }
}
