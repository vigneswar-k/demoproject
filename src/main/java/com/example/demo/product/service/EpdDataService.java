package com.example.demo.product.service;

import com.example.demo.product.document.Category;
import com.example.demo.product.document.Product;
import com.example.demo.product.dto.CategoryDTO;
import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling product-related operations.
 */
@Service
public class EpdDataService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    /**
     * Constructor for EpdDataService.
     *
     * @param resourceLoader  Resource loader to read files from the classpath.
     * @param objectMapper    ObjectMapper for JSON parsing.
     * @param productRepository Repository for performing product-related database operations.
     */
    public EpdDataService(ResourceLoader resourceLoader, ObjectMapper objectMapper, ProductRepository productRepository) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    /**
     * Loads default product data from a JSON file and stores it in the database.
     *
     * @param file The JSON file name located in the classpath.
     * @return A success message indicating the data was created.
     */
    public String createDefaultValues(String file) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + file);
            InputStream inputStream = resource.getInputStream();
            List<Product> productList = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
            productRepository.saveAll(productList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the JSON file", e);
        }
        return "Data Created successfully";
    }

    /**
     * Retrieves all products from the database and converts them into DTOs.
     *
     * @return A list of ProductDTO objects.
     */
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Product entity to a ProductDTO.
     *
     * @param product The Product entity.
     * @return The converted ProductDTO.
     */
    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        if (!CollectionUtils.isEmpty(product.getCategories())) {
            List<CategoryDTO> categoryDTOS = product.getCategories().stream()
                    .map(this::convertToCategoryDTO)
                    .collect(Collectors.toList());
            productDTO.setCategories(categoryDTOS);
        }

        return productDTO;
    }

    /**
     * Converts a Category entity to a CategoryDTO.
     *
     * @param category The Category entity.
     * @return The converted CategoryDTO.
     */
    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    /**
     * Retrieves all products belonging to a specific category.
     *
     * @param categoryName The name of the category.
     * @return A list of ProductDTO objects.
     */
    public List<ProductDTO> getAllByCategoryName(String categoryName) {
        return productRepository.findByCategoriesName(categoryName).stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all products that contain a specific tag.
     *
     * @param tagName The name of the tag.
     * @return A list of ProductDTO objects.
     */
    public List<ProductDTO> getAllBytagName(String tagName) {
        return productRepository.findByTagsContaining(tagName).stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }
}
