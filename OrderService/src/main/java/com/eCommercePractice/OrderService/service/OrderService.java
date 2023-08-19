package com.eCommercePractice.OrderService.service;

import com.eCommercePractice.OrderService.modal.OrderRequest;
import com.eCommercePractice.OrderService.modal.OrderResponse;

public interface OrderService {
    OrderResponse getOrderDetails(long id);
    long placeOrder(OrderRequest orderRequest);
}
