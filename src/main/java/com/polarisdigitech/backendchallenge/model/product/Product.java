package com.polarisdigitech.backendchallenge.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@NamedStoredProcedureQuery(
        name = "findTotalProductsByPrice",
        procedureName = "GET_TOTAL_PRODUCTS_BY_PRICE",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "price_in", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)
        }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private LocalDateTime createdDate = LocalDateTime.now();

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
