package com.example.demo.dao;

import com.example.demo.dto.ProductRequest;
import com.example.demo.model.Product;

public interface ProductDao {

    public Product getProductById(Integer productId);
    public Integer createProduct(ProductRequest request);
    public void updateProduct(Integer productId, ProductRequest request);
    public void deleteProductById(Integer productId);
}
