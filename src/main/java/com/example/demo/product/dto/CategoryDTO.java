package com.example.demo.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for Category.
 * This class is used to transfer category data between layers.
 */
@Data
public class CategoryDTO {

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
