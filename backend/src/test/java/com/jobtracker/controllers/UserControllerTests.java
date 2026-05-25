package com.jobtracker.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.models.entities.User;
import com.jobtracker.models.entities.Document;
import com.jobtracker.models.entities.JobApplication;
import com.jobtracker.models.entities.Company;
import com.jobtracker.models.entities.Contact;
import com.jobtracker.services.UserService;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User firstUser;
    private User secondUser;

    @BeforeEach
    public void setUp() {
        this.firstUser = new User(
            UUID.randomUUID(),
            "AnEmail@yahoo.com",
            "Kris Bali",
            "someHashedPassword",
            "Software Engineer", 
            Instant.now(),
            Instant.now(),
            Instant.now(),
            new ArrayList<JobApplication>(),
            new ArrayList<Company>(),
            new ArrayList<Contact>(),
            new ArrayList<Document>()    
        );

        this.secondUser = new User(
            UUID.randomUUID(),
            "AnotherEmail@yahoo.com",
            "Bali Kris",
            "anotherHashedPassword",
            "Senior Software Engineer", 
            Instant.now(),
            Instant.now(),
            Instant.now(),
            new ArrayList<JobApplication>(),
            new ArrayList<Company>(),
            new ArrayList<Contact>(),
            new ArrayList<Document>()
        );
    }

    @Test
    public void createUser() throws Exception {
        Mockito.when(userService.createUser(any(User.class)))
            .thenReturn(this.firstUser);

        mockMvc.perform(post("/api/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstUser)))
            .andExpect(status().isCreated()
        )
        .andExpect(jsonPath("$.id").value(this.firstUser.getId().toString()))
        .andExpect(jsonPath("$.email").value(this.firstUser.getEmail()))
        .andExpect(jsonPath("$.displayName").value(this.firstUser.getDisplayName()))
        .andExpect(jsonPath("$.profession").value(this.firstUser.getProfession()));
    }

    @Test
    public void createUserWithInvalidData() throws Exception {
        this.firstUser.setEmail("");

        mockMvc.perform(post("/api/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstUser))
        )
        .andExpect(status().isBadRequest());
    }
}
