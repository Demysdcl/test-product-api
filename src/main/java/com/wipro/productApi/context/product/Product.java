package com.wipro.productApi.context.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
public class Product {

    @Id
    @Column(name = "product_id")
    private Long id;
    private String description;
    private BigDecimal unitValue;
    private LocalDate creationDate;
    private Boolean enable;

    public Product(Long id, String description, BigDecimal unitValue) {
        this.id = id;
        this.description = description;
        this.unitValue = unitValue;
        this.creationDate = LocalDate.now();
        this.enable = true;
    }
}
