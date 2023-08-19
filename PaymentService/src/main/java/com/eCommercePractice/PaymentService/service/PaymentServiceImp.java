package com.eCommercePractice.PaymentService.service;

import com.eCommercePractice.PaymentService.entity.TransactionDetails;
import com.eCommercePractice.PaymentService.modal.PaymentMode;
import com.eCommercePractice.PaymentService.modal.PaymentRequest;
import com.eCommercePractice.PaymentService.modal.PaymentResponse;
import com.eCommercePractice.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImp implements PaymentService{

    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Payment Details Service calling...");

        TransactionDetails transactionDetails = TransactionDetails
                .builder()
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentDate(Instant.now())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.getAmount())
                .orderId(paymentRequest.getOrderId())
                .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction Created with ID {}",transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {

        TransactionDetails transactionDetails
                = transactionDetailsRepository.findByOrderId(orderId);
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .status(transactionDetails.getPaymentStatus())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .build();

        return paymentResponse;
    }
}
