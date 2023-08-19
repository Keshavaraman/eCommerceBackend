package com.eCommercePractice.PaymentService.service;

import com.eCommercePractice.PaymentService.modal.PaymentRequest;
import com.eCommercePractice.PaymentService.modal.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
