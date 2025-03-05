package com.example.demo.product.controller;

import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.service.EpdDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing product-related operations.
 */
@RestController
public class EpdDataController {

    private final EpdDataService epdDataService;

    /**
     * Constructor injection for EpdDataService.
     *
     * @param epdDataService The service handling product data operations.
     */
    public EpdDataController(EpdDataService epdDataService) {
        this.epdDataService = epdDataService;
    }

    /**
     * Initializes the database with default values from "default.json".
     *
     * @return A success message upon initialization.
     */
    @PostMapping(value = "/init", produces = "application/json")
    public String createDefaultValues() {
        return epdDataService.createDefaultValues("default.json");
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    @GetMapping(path = "/show_all")
    public List<ProductDTO> getAll() {
        return epdDataService.getAll();
    }

    /**
     * Retrieves all products that belong to a specific category.
     *
     * @param categoryName The name of the category.
     * @return A list of products in the specified category.
     */
    @GetMapping("/categories/{cat_name}")
    public List<ProductDTO> getAllByCategoryName(@PathVariable("cat_name") String categoryName) {
        return epdDataService.getAllByCategoryName(categoryName);
    }

    /**
     * Retrieves all products associated with a specific tag.
     *
     * @param tagName The tag name.
     * @return A list of products that contain the given tag.
     */
    @GetMapping("/tags/{tagname}")
    public List<ProductDTO> getAllByTagName(@PathVariable("tagname") String tagName) {
        return epdDataService.getAllBytagName(tagName);
    }
}
