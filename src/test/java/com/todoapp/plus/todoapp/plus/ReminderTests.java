package com.todoapp.plus.todoapp.plus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.plus.todoapp.plus.models.Reminder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReminderTests extends BaseTest {
    @BeforeEach
    public void setup() {
        initialize();
    }

    @Test
    public void testCreateReminder() throws Exception {
        Reminder reminder = new Reminder(new Date(), "reminder");
        String json = new ObjectMapper().writeValueAsString(reminder);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/todo/1/reminder")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testListReminders() throws Exception {
        Reminder reminder = new Reminder(new Date(), "reminder");
        String json = new ObjectMapper().writeValueAsString(reminder);

        mvc.perform(MockMvcRequestBuilders.post("/todo/1/reminder")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/reminder")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        Reminder[] reminders = new ObjectMapper().readValue(response.getContentAsString(), Reminder[].class);

        assertTrue(reminders.length > 0);
    }

    @Test
    public void testGetReminder() throws Exception {
        Reminder newReminder = new Reminder(new Date(), "reminder");
        String json = new ObjectMapper().writeValueAsString(newReminder);

        mvc.perform(MockMvcRequestBuilders.post("/todo/1/reminder")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/reminder/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        Reminder reminder = new ObjectMapper().readValue(response.getContentAsString(), Reminder.class);

        assertEquals("reminder", reminder.getName());
    }
}
