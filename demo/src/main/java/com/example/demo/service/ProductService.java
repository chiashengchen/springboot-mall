package com.example.demo.service;

import com.example.demo.constant.ProductCategory;
import com.example.demo.dto.ProductQueryParam;
import com.example.demo.dto.ProductRequest;
import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Integer productId);
    public Integer createProduct(ProductRequest request);
    public void updateProduct(Integer productId, ProductRequest request);
    public void deleteProductById(Integer productId);

    List<Product> getProducts(ProductQueryParam queryParam);
}
