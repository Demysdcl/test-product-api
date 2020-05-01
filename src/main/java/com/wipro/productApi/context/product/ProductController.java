package com.wipro.productApi.context.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/enable")
    public ResponseEntity<List<Product>> getEnableProducts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity
                .ok(this.productService.getEnableProducts(page,size));
    }
}
