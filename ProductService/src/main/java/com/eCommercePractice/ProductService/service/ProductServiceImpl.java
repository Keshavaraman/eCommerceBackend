package com.eCommercePractice.ProductService.service;

import com.eCommercePractice.ProductService.entity.Product;
import com.eCommercePractice.ProductService.exception.ProductServiceCustomException;
import com.eCommercePractice.ProductService.modal.ProductRequest;
import com.eCommercePractice.ProductService.modal.ProductResponse;
import com.eCommercePractice.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product....");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product Created...");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product {}",productId);
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product not found","ERR_PRD_1"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reducerQuantity(long productId, long quantity) {
        log.info("Reduce Qunatity {} for Id:{}",quantity,productId);
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("ProductId not found","ERR_PRD_2"));

        if(product.getQuantity()<quantity) {
           throw new ProductServiceCustomException("Product does not have sufficient stock","ERR_PRD_3");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product Quantity Reduced Successfully");
    }
}
