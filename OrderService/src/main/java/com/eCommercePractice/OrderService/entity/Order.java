package com.eCommercePractice.OrderService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private long productId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
}
