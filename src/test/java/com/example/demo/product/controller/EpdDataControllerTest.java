package com.example.demo.product.controller;

import com.example.demo.product.dto.CategoryDTO;
import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.service.EpdDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EpdDataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EpdDataService epdDataService;

    @InjectMocks
    private EpdDataController epdDataController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(epdDataController).build();
    }

    @Test
    void testCreateDefaultValues() throws Exception {
        when(epdDataService.createDefaultValues("default.json")).thenReturn("Database initialized");

        mockMvc.perform(post("/init")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Database initialized"));
    }

    @Test
    void testGetAll() throws Exception {
        ProductDTO productDTO = createSampleProductDTO();
        when(epdDataService.getAll()).thenReturn(Collections.singletonList(productDTO));

        mockMvc.perform(get("/show_all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllByCategoryName() throws Exception {
        ProductDTO productDTO = createSampleProductDTO();
        when(epdDataService.getAllByCategoryName("Insulation")).thenReturn(Collections.singletonList(productDTO));

        mockMvc.perform(get("/categories/Insulation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllByTagName() throws Exception {
        ProductDTO productDTO = createSampleProductDTO();
        when(epdDataService.getAllBytagName("bio-based")).thenReturn(Collections.singletonList(productDTO));

        mockMvc.perform(get("/tags/bio-based")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private ProductDTO createSampleProductDTO() {
        ProductDTO product = new ProductDTO();
        product.setId("67c9a5a3f9fd815cb5df63f2");
        product.setUuid("75ee-4baa-0d835bb-a7c0-e174005169f7");
        product.setName("Expanded cork");

        CategoryDTO category = new CategoryDTO();
        category.setId(4);
        category.setName("Insulation");
        product.setCategories(List.of(category));

        product.setTags(List.of("bio-based"));

        return product;
    }
}