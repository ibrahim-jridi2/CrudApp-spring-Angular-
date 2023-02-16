package com.numeryx.product.service.impl;

import com.numeryx.product.dto.ProductDto;
import com.numeryx.product.entities.Product;
import com.numeryx.product.mapper.ProductMapperImpl;
import com.numeryx.product.repository.ProductRepo;
import com.numeryx.product.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapperImpl productMapper;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapperImpl productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    private final Logger logger = Logger.getLogger(ProductServiceImpl.class);



    @Override
    public ProductDto createProduct(ProductDto product) {
        if(productRepo.existsByName(product.getName())){
            throw new RuntimeException("product already exist");
        }
        Product entity = this.productMapper.toEntity(product);

        Product savedEntity = productRepo.save(entity);
        ProductDto productDto = productMapper.toDto(savedEntity);

        return productDto;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return  productMapper.toDto(productRepo.findAll());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.toDto(productRepo.getOne(id));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto,Long id) {


        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {

                Product ProductToSave = productMapper.toEntity(productDto);

                optionalProduct.get().setName(ProductToSave.getName());
                optionalProduct.get().setPrice(ProductToSave.getPrice());
                optionalProduct.get().setCategory(ProductToSave.getCategory());
//            if (productRepo.existsByName(ProductToSave.getName())){
                ProductDto Product = productMapper.toDto(productRepo.save( optionalProduct.get()));

                return Product;
//            }else {
//                throw new RuntimeException("Product with name " + productDto.getName() + " is already exist.");
//            }

        } else {
            throw new EntityNotFoundException("Product with id " + productDto.getId() + " is not found.");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> Product = productRepo.findById(id);
        if (Product.isPresent()) {
            productRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public List<ProductDto> search(String query) {
        return productMapper.toDto(productRepo.findByNameContainingIgnoreCase(query));
    }

    /*@Override
    public ProductDto getByName(String name) {
        if (productRepo.findByName(name) != null) {
            return productMapper.toDto(productRepo.findByName(name));
        }  else return null;
    }*/
}
