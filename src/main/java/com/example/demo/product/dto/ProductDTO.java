package com.example.demo.product.dto;

import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) for Product.
 * This class is used to transfer product data between layers.
 */
@Data
public class ProductDTO {

    /**
     * The unique identifier for the product.
     */
    private String id;

    /**
     * A universally unique identifier (UUID) for the product.
     */
    private String uuid;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The list of categories associated with the product.
     */
    private List<CategoryDTO> categories;

    /**
     * A list of tags associated with the product.
     */
    private List<String> tags;
}
