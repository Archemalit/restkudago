package org.tbank.restkudago.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.tbank.restkudago.model.Category;
import org.tbank.restkudago.service.CategoryService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = {CategoryRestController.class})
public class CategoryRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("Test controller")
    public void testCreateCategory() throws Exception {
        // given
        String payload = """
                {
                    "slug" : "cinema",
                    "name" : "Cinema in Surf Coffee"
                }
                """;
        Category category = new Category("cinema", "Cinema in Surf Coffee");
        category.setId(1L);
        when(categoryService.createCategory("cinema", "Cinema in Surf Coffee")).thenReturn(category);

        // when
        mockMvc.perform(post("/api/v1/places/categories")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1/places/categories/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":1,\"slug\":\"cinema\",\"name\":\"Cinema in Surf Coffee\"}"));;
    }

    @Test
    @DisplayName("Test of deleting category")
    public void testDeleteCategory() throws Exception {
        Long categoryId = 1L;

        mockMvc.perform(delete("/api/v1/places/categories/{categoryId}", categoryId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Test of updating category")
    public void testUpdateCategory() throws Exception {
        // given
        Long categoryId = 1L;
        String payload = """
                {
                    "slug" : "cinema",
                    "name" : "Cinema in Surf Coffee"
                }
                """;
        Category category = new Category("cinema", "Cinema in Surf Coffee");
        category.setId(categoryId);
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);

        // when
        mockMvc.perform(put("/api/v1/places/categories/{categoryId}", categoryId)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("Test for find category by id")
    public void testGetCategoryById() throws Exception {
        // given
        Long categoryId = 1L;
        Category category = new Category("cinema", "Cinema in Surf Coffee");
        category.setId(categoryId);
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);

        // when
        mockMvc.perform(get("/api/v1/places/categories/{categoryId}", categoryId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("{\"id\":1,\"slug\":\"cinema\",\"name\":\"Cinema in Surf Coffee\"}"));

    }

    @Test
    @DisplayName("Test for find all categories")
    public void testGetAllCategories() throws Exception {
        // given
        List<Category> categories = List.of(
                new Category("cinema", "Cinema in Surf Coffee"),
                new Category("pool", "Party in swimming pool")
        );

        when(categoryService.findAllCategories()).thenReturn(categories);

        // when
        mockMvc.perform(get("/api/v1/places/categories"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[{\"id\":null,\"slug\":\"cinema\",\"name\":\"Cinema in Surf Coffee\"},{\"id\":null,\"slug\":\"pool\",\"name\":\"Party in swimming pool\"}]"));
    }
}
