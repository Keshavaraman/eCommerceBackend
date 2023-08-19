package com.eCommercePractice.ProductService.modal;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private long price;
    private long quantity;
}
