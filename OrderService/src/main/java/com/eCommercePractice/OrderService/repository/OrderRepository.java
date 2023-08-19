package com.eCommercePractice.OrderService.repository;

import com.eCommercePractice.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
