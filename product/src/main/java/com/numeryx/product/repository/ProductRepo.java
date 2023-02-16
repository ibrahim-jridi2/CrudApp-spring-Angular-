package com.numeryx.product.repository;

import com.numeryx.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByName(String userName);
    boolean existsByName(String username);
    List<Product> findByNameContainingIgnoreCase(String query);
}
