package com.eCommercePractice.ProductService.repository;

import com.eCommercePractice.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
