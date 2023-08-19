package com.eCommercePractice.ProductService.service;

import com.eCommercePractice.ProductService.modal.ProductRequest;
import com.eCommercePractice.ProductService.modal.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reducerQuantity(long productId, long quantity);
}
