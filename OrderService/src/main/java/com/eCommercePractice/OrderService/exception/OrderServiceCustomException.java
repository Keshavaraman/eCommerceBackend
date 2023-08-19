package com.eCommercePractice.OrderService.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Data
public class OrderServiceCustomException extends RuntimeException{

    private String errorCode;
    private int status;
    public OrderServiceCustomException(String message,String ErrorCode,int status) {
        super(message);
        this.errorCode=ErrorCode;
        this.status=status;
    }
}
