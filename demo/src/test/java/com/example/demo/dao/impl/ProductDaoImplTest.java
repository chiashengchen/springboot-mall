package com.example.demo.dao.impl;

import com.example.demo.constant.ProductCategory;
import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductRequest;
import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDaoImplTest {

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Test
    public void selectById() {
        Product product = productDao.getProductById(1);
        assertNotNull(product);
    }

    //@Transactional
    @Test
    public void createProduct() {
        ProductRequest request = new ProductRequest();
        request.setProductName("to");
        request.setCategory(ProductCategory.valueOf("CAR"));
        request.setImageUrl("123");
        request.setStock(3);
        request.setPrice(10000);

        int productId = productDao.createProduct(request);
        assertEquals(5, productId);
    }
}