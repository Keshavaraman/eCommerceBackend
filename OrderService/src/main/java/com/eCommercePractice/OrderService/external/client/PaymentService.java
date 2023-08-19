package com.eCommercePractice.OrderService.external.client;

import com.eCommercePractice.OrderService.exception.OrderServiceCustomException;
import com.eCommercePractice.OrderService.external.modal.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name="Payment-Service/payment")
public interface PaymentService {
    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception e){
      throw new OrderServiceCustomException("Payment Service not accessible ","ERR_INT_1",500);
    }


}
