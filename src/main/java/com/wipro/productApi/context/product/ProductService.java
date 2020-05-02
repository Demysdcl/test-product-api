package com.wipro.productApi.context.product;

import com.wipro.productApi.exception.ObjectNotFoundExpection;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getEnableProducts(int page, int size) {
        return this.productRepository.findByEnable(true, PageRequest.of(page - 1, size));
    }

    public List<Product> getDisableProducts(int page, int size) {
        return this.productRepository.findByEnable(false, PageRequest.of(page - 1, size));
    }

    public Product getProductById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundExpection(
                        String.format("Nenhum produto encontro com o código: %d", id)));
    }

}
