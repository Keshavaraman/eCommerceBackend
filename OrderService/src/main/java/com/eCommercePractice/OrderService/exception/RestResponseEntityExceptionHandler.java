package com.eCommercePractice.OrderService.exception;

import com.eCommercePractice.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleOrderServiceException(OrderServiceCustomException orderServiceCustomException) {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(orderServiceCustomException.getMessage())
                .errorCode(orderServiceCustomException.getErrorCode())
                .build(), HttpStatus.valueOf(orderServiceCustomException.getStatus())
        );
    }

}
