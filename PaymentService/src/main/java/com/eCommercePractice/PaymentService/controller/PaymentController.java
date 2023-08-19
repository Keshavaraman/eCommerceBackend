package com.eCommercePractice.PaymentService.controller;

import com.eCommercePractice.PaymentService.modal.PaymentRequest;
import com.eCommercePractice.PaymentService.modal.PaymentResponse;
import com.eCommercePractice.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("orderId") long orderId) {
        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);
        return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
    }
}
