package com.example.demo.dao.impl;

import com.example.demo.constant.ProductCategory;
import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductQueryParam;
import com.example.demo.dto.ProductRequest;
import com.example.demo.model.Product;
import com.example.demo.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParam queryParam) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date" +
                " FROM product WHERE 1=1";
        // WHERE 1=1 是個廢話，但她可以滿足如果需要增加字串的話，用 AND 的方式增加
        Map<String, Object> map = new HashMap<>();
        ProductCategory category = queryParam.getCategory();
        String search = queryParam.getSearch();
        String orderBy = queryParam.getOrderBy();
        String sort = queryParam.getSort();
        if(category != null) {
            sql = sql + " AND category = :category";
            map.put("category", category.name());
        }
        if(search != null) {
            sql = sql + " AND product_name LIKE :search";
            // % 代表任意符號，一定要加在 map
            map.put("search", "%" + search + "%");
        }
        // 不能用 map 的方式
        sql = sql + " ORDER BY " + orderBy + " " + sort;
        List<Product> products = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return products;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date" +
                " FROM product WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest request) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, " +
                "price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", request.getProductName());
        map.put("category", request.getCategory().toString());
        map.put("imageUrl", request.getImageUrl());
        map.put("price", request.getPrice());
        map.put("stock", request.getStock());
        map.put("description", request.getDescription());
        Date date = new Date();
        map.put("lastModifiedDate", date);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);
    }
}
