package com.wipro.productApi.context.product;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getEnableProducts(int page, int size) {
        return this.productRepository.findByEnable(true, PageRequest.of(page - 1, size));
    }
}
