package com.eCommercePractice.OrderService.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails {
    private String productName;
    private long productId;
    private long quantity;
    private long price;
}
