package com.wipro.productApi.context.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/disable")
    public ResponseEntity<List<Product>> getDisableProducts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity
                .ok(this.productService.getDisableProducts(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity
                .ok(this.productService.getProductById(id));
    }
}
