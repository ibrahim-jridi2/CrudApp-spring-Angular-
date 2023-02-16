package com.numeryx.product.controller;

import com.numeryx.product.dto.ProductDto;
import com.numeryx.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/products")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto Product) {
        ProductDto createdProduct = productService.createProduct(Product);
        return ResponseEntity.created(URI.create("/Products/" + createdProduct.getId())).body(createdProduct);
    }



    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") long id,@RequestBody ProductDto ProductDto) {
        ProductDto updatedProduct = productService.updateProduct(ProductDto,id);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/searchProduct/{query}")
    public ResponseEntity<List<ProductDto>> search(@PathVariable String query) {
        try {
            List<ProductDto> results = productService.search(query);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
   /* @PostMapping("/by-Productnames")
    public ResponseEntity<?> getProductsByProductname(@RequestBody String Productnames) {
        return ResponseEntity.ok(productService.getByName(Productnames));
    }*/

}
