package com.numeryx.product.service;

import com.numeryx.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto product);
    List<ProductDto> findAllProducts();
    ProductDto getProductById(Long id);
    ProductDto updateProduct(ProductDto productDto,Long id );
    void deleteProduct(Long id);
    /*ProductDto getByName(String name);*/
    List<ProductDto> search(String query);
}
