package com.example.demo.product.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a product category.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Category {

    /**
     * The unique identifier for the category.
     */
    @JsonProperty("id")
    private int id;

    /**
     * The name of the category.
     */
    @JsonProperty("name")
    private String name;
}
