package com.example.demo.dao.impl;

import com.example.demo.dao.ProductDao;
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
}