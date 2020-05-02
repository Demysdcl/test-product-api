package com.wipro.productApi.context.product;

import com.wipro.productApi.exception.ObjectNotFoundExpection;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

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
                .orElseThrow(getObjectNotFoundExpectionSupplier(id));
    }

    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return this.productRepository.findById(id)
                .map(item -> this.productRepository.save(product))
                .orElseThrow(getObjectNotFoundExpectionSupplier(id));
    }

    public Product disableProductById(Long id) {
        return this.productRepository.findById(id)
                .map(item -> {
                    item.setEnable(false);
                    return this.productRepository.save(item);
                }).orElseThrow(getObjectNotFoundExpectionSupplier(id));
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        this.productRepository.findById(id)
                .map(item -> {
                    this.productRepository.deleteById(id);
                    return null;
                }).orElseThrow(getObjectNotFoundExpectionSupplier(id));
    }

    private Supplier<ObjectNotFoundExpection> getObjectNotFoundExpectionSupplier(Long id) {
        return () -> new ObjectNotFoundExpection(
                String.format("Nenhum produto encontrado com o código: %d", id));
    }

}
