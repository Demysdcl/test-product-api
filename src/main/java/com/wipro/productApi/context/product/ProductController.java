package com.wipro.productApi.context.product;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api("Endpoints para consulta, edição, remoção e criação de produtos")
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

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable  Long id, @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.updateProduct(id, product));
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<Product> disableProductById(@PathVariable  Long id) {
        return ResponseEntity.ok(this.productService.disableProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(this.productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
