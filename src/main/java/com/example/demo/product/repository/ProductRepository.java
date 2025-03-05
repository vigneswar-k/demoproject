package com.example.demo.product.repository;

import com.example.demo.product.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Product entities in MongoDB.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    /**
     * Finds products that belong to a specific category.
     *
     * @param categoryName The name of the category.
     * @return A list of products that belong to the given category.
     */
    List<Product> findByCategoriesName(String categoryName);

    /**
     * Finds products that contain a specific tag.
     *
     * @param tagName The name of the tag.
     * @return A list of products associated with the given tag.
     */
    List<Product> findByTagsContaining(String tagName);
}
