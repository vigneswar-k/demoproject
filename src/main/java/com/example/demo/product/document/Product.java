package com.example.demo.product.document;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Represents a product entity stored in MongoDB.
 * Each product can belong to multiple categories and have multiple tags.
 *
 * @author Vigneswar K
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "Product")
@TypeAlias(value = "Product")
public class Product {

    /**
     * The unique identifier for the product in MongoDB.
     */
    @Id
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
     * The list of categories the product belongs to.
     */
    private List<Category> categories;

    /**
     * A list of tags associated with the product.
     */
    private List<String> tags;
}
