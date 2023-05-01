package com.todoapp.plus.todoapp.plus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.plus.todoapp.plus.models.Category;
import com.todoapp.plus.todoapp.plus.models.TodoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTests extends BaseTest {

    @BeforeEach
    public void setup() {
        initialize();
    }

    @Test
    public void testCreateTodo() throws Exception {
        Category category = new Category("tasks");
        TodoModel todo = new TodoModel("item", new Date(), "an item", 1, category);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/todo")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(todo))).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
        String resultString = response.getContentAsString();

        TodoModel responseTodo = new ObjectMapper().readValue(resultString, TodoModel.class);

        assertEquals("item", responseTodo.getTitle());
        assertEquals("an item", responseTodo.getDescription());
        assertEquals(1, responseTodo.getPriority());
    }

    @Test
    public void testListTodo() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/todo")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = result.getResponse();
        TodoModel[] items = new ObjectMapper().readValue(response.getContentAsString(), TodoModel[].class);

        //we expect at least the one default task. If multiple tests run, others could be created.
        assertTrue(items.length > 0);

        assertEquals("Create Spring Project", items[0].getTitle());
    }

    @Test
    public void testGetTodo() throws Exception {
        Category category = new Category("tasks");
        TodoModel todo = new TodoModel("item", new Date(), "an item", 1, category);

        mvc.perform(MockMvcRequestBuilders.post("/todo")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(todo))).andReturn();

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/todo/2")).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }

    private String toJson(TodoModel todo) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(todo);
    }
}
