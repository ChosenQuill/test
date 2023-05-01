package com.todoapp.plus.todoapp.plus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.plus.todoapp.plus.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTests extends BaseTest {
    @BeforeEach
    public void setup() {
        initialize();
    }

    @Test
    public void testCreateCategory() throws Exception {
        Category newCategory = new Category("new-category");
        String jsonString = new ObjectMapper().writeValueAsString(newCategory);
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/category")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testListCategories() throws Exception {
        Category newCategory = new Category("new-category");
        String jsonString = new ObjectMapper().writeValueAsString(newCategory);
        mvc.perform(MockMvcRequestBuilders.post("/category")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)).andReturn();

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/category")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        Category[] categories = new ObjectMapper().readValue(response.getContentAsString(), Category[].class);

        assertTrue(categories.length > 0);

        assertEquals("tasks", categories[0].getName());
        assertEquals("alarms", categories[1].getName());
    }

    @Test
    public void testGetCategoryById() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/category/2")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        Category category = new ObjectMapper().readValue(response.getContentAsString(), Category.class);

        assertEquals("alarms", category.getName());
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Category newCategory = new Category("new-category");
        String jsonString = new ObjectMapper().writeValueAsString(newCategory);
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/category")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)).andReturn().getResponse();

        Category responseCategory = new ObjectMapper().readValue(response.getContentAsString(), Category.class);

        MockHttpServletResponse deleteResponse = mvc.perform(MockMvcRequestBuilders
                .delete("/category/" + responseCategory.getId())
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(200, deleteResponse.getStatus());
    }
}
