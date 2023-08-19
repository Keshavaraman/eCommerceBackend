package com.eCommercePractice.OrderService.external.decoder;

import com.eCommercePractice.OrderService.exception.OrderServiceCustomException;
import com.eCommercePractice.OrderService.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("::{}",response.request().url());
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
            throw new OrderServiceCustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());
        } catch (IOException e) {
            throw new OrderServiceCustomException("Internal Server Error","INT_ERR_1",500);
        }
    }
}
