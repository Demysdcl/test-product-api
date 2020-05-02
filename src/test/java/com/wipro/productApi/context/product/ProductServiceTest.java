package com.wipro.productApi.context.product;

import com.wipro.productApi.exception.ObjectNotFoundExpection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    private List<Product> products;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productRepository);
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
    void should_return_only_enable_products() {
        when(this.productRepository.findByEnable(true, PageRequest.of(0, 5)))
                .thenReturn(this.products.stream()
                        .filter(Product::getEnable)
                        .collect(Collectors.toList())
                );

        List<Product> enableProducts = this.productService.getEnableProducts(1, 5);
        assertFalse(enableProducts.isEmpty());
        assertEquals(5, enableProducts.size());
        enableProducts.forEach(item -> assertTrue(item.getEnable()));
    }

    @Test
    void should_return_only_disable_products() {
        when(this.productRepository.findByEnable(false, PageRequest.of(0, 5)))
                .thenReturn(this.products.stream()
                        .filter(item -> !item.getEnable())
                        .collect(Collectors.toList())
                );

        List<Product> disableProducts = this.productService.getDisableProducts(1, 5);
        assertFalse(disableProducts.isEmpty());
        assertEquals(5, disableProducts.size());
        disableProducts.forEach(item -> assertFalse(item.getEnable()));
    }

    @Test
    void should_return_product_with_id_1l() {
        given_id_then_return(1L, Optional.of(products.get(0)));
        Product product = this.productService.getProductById(1L);
        assertNotNull(product);
        assertEquals("arroz", product.getDescription());
    }

    @Test
    void should_throws_object_not_found_exception_when_try_to_find_id_20() {
        String message = config_message_when_id20_and_optional_empty();
        Exception exception = assertThrows(ObjectNotFoundExpection.class, () -> {
            this.productService.getProductById(20L);
        });
        assertEquals(
                message,
                exception.getMessage()
        );
    }

    @Test
    void should_update_product_with_id_1l() {
        Product findProduct = products.get(0);
        findProduct.setUnitPrice(BigDecimal.TEN);
        findProduct.setDescription("Notebook");

        given_id_then_return(1L, Optional.of(products.get(0)));
        when_save_product_then_return_product();

        Product product = this.productService.updateProduct(1l, findProduct);
        assertNotNull(product);
        assertEquals("Notebook", product.getDescription());
        assertEquals(BigDecimal.TEN, product.getUnitPrice());
    }

    @Test
    void should_throw_object_not_found_exception_when_try_update_with_id_20() {
        String message = config_message_when_id20_and_optional_empty();
        Exception exception = assertThrows(ObjectNotFoundExpection.class, () -> {
            this.productService.updateProduct(20L, products.get(0));
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void should_to_disable_product_with_id_1l() {
        given_id_then_return(1l, Optional.of(products.get(0)));
        when_save_product_then_return_product();
        Product disableProduct = this.productService.disableProductById(1l);
        assertNotNull(disableProduct);
        assertFalse(disableProduct.getEnable());
    }

    @Test
    void should_thrown_object_not_found_exception_when_disable_id20() {
        String message = config_message_when_id20_and_optional_empty();
        Exception exception = assertThrows(ObjectNotFoundExpection.class, () -> {
            this.productService.disableProductById(20L);
        });
        assertEquals(message, exception.getMessage());
    }

    private void when_save_product_then_return_product() {
        when(this.productRepository.save(any(Product.class)))
                .thenAnswer(returnsFirstArg());
    }

    private String config_message_when_id20_and_optional_empty() {
        given_id_then_return(20l, Optional.empty());
        return "Nenhum produto encontrado com o código: 20";
    }

    private void given_id_then_return(long id, Optional<Product> toReturn) {
        when(this.productRepository.findById(id))
                .thenReturn(toReturn);
    }
}