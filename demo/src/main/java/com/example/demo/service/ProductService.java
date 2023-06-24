package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.model.Product;

public interface ProductService {
    public Product getProductById(Integer productId);
    public Integer createProduct(ProductRequest request);
}
