package com.eCommercePractice.OrderService.external.client;

import com.eCommercePractice.OrderService.exception.OrderServiceCustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name="Product-Service/product")
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity
            (@PathVariable("id") long productId,
             @RequestParam long quantity);
    default void fallback(Exception e){
        throw new OrderServiceCustomException("Payment Service not accessible ","ERR_INT_1",500);
    }
    }
