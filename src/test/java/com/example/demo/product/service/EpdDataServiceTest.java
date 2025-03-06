package com.example.demo.product.service;

import com.example.demo.product.document.Category;
import com.example.demo.product.document.Product;
import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EpdDataServiceTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private EpdDataService epdDataService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId("67c9a5a3f9fd815cb5df63f2");
        product.setUuid("75ee-4baa-0d835bb-a7c0-e174005169f7");
        product.setName("Expanded cork");
        product.setCategories(Collections.singletonList(new Category(4, "Insulation")));
        product.setTags(List.of("bio-based"));
    }

    @Test
    void createDefaultValues_shouldSaveProducts() throws IOException {
        Resource resource = mock(Resource.class);
        InputStream inputStream = mock(InputStream.class);

        when(resourceLoader.getResource(any())).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);
        when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class)))
                .thenReturn(List.of(product));

        String result = epdDataService.createDefaultValues("default.json");

        assertEquals("Data Created successfully", result);
        verify(productRepository, times(1)).saveAll(any());
    }

    @Test
    void getAll_shouldReturnProductDTOs() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductDTO> products = epdDataService.getAll();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Expanded cork", products.get(0).getName());
    }

    @Test
    void getAllByCategoryName_shouldReturnProducts() {
        when(productRepository.findByCategoriesName("Insulation")).thenReturn(List.of(product));
        List<ProductDTO> products = epdDataService.getAllByCategoryName("Insulation");

        assertFalse(products.isEmpty());
        assertEquals("Expanded cork", products.get(0).getName());
    }

    @Test
    void getAllByTagName_shouldReturnProducts() {
        when(productRepository.findByTagsContaining("bio-based")).thenReturn(List.of(product));
        List<ProductDTO> products = epdDataService.getAllBytagName("bio-based");

        assertFalse(products.isEmpty());
        assertEquals("Expanded cork", products.get(0).getName());
    }
}
