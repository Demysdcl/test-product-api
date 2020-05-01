package com.wipro.productApi.context.product;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Mock
    private ProductService productService;

    private List<Product> products;

    @BeforeEach
    public void init() {
        products = Stream.of(
                "1-arroz", "2-feijão", "3-sal", "4-macarrão", "5-bolacha",
                "6-açucar", "7-leite", "8-café", "9-carne", "10-frango"
        ).map(item -> {
            String [] splits = item.split("-");
            Long value = Long.valueOf(splits[0]);
            return new Product(value, splits[1], BigDecimal.valueOf(value * 1.4));
        }).peek(product -> {
            product.setEnable(product.getId() % 2 == 0);
        }).collect(Collectors.toList());
    }

    @Test
    void getEnableProducts() {
        Mockito.when(this.productService.getEnableProducts(1, 5))
                .thenReturn(this.products.stream()
                        .filter(Product::getEnable)
                        .collect(Collectors.toList())
                );

        List<Product> enableProducts = this.productService.getEnableProducts(1, 5);
        assertFalse(enableProducts.isEmpty());
        assertEquals(5, enableProducts.size());
    }
}