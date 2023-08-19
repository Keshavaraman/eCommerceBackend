package com.eCommercePractice.ProductService.controller;

import com.eCommercePractice.ProductService.modal.ProductRequest;
import com.eCommercePractice.ProductService.modal.ProductResponse;
import com.eCommercePractice.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") long productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity
            (@PathVariable("id") long productId,@RequestParam long quantity) {
        productService.reducerQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
