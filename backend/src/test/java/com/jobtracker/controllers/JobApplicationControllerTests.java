package com.jobtracker.controllers;

import java.time.LocalDate;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.models.JobApplication;
import com.jobtracker.services.JobApplicationService;
import com.jobtracker.models.StatusType;

@WebMvcTest(JobApplicationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class JobApplicationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobApplicationService jobApplicationService;

    @Autowired
    private ObjectMapper objectMapper;

    private JobApplication firstJobApplication;
    private JobApplication secondJobApplication;

    @BeforeEach
    public void setup() {
        this.firstJobApplication = new JobApplication(
            UUID.randomUUID(),
            "Software Engineer",
            "New York, NY",
            "Full-time",
            "https://example.com/apply",
            80000.00,
            120000.00,
            LocalDate.now(),
            LocalDate.now().plusDays(30),
            StatusType.APPLIED,
            "LinkedIn"
        );

        this.secondJobApplication = new JobApplication(
            UUID.randomUUID(),
            "Data Scientist",
            "San Francisco, CA",
            "Full-time",
            "https://example.com/apply",
            90000.00,
            130000.00,
            LocalDate.now(),
            LocalDate.now().plusDays(30),
            StatusType.APPLIED,
            "Indeed"
        );
    }

    @Test
    public void createJobApplication() throws Exception {
        Mockito.when(jobApplicationService.createJobApplication(any(JobApplication.class)))
            .thenReturn(this.firstJobApplication);

        MvcResult result = mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isCreated())
        .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        JobApplication createdJobApplication = objectMapper.readValue(responseContent, JobApplication.class);
        assertEquals(this.firstJobApplication.getId(), createdJobApplication.getId());
    }

    @Test
    public void createInvalidJobApplication() throws Exception {
        this.firstJobApplication.setJobTitle(" ");

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteJobApplication() throws Exception {
        Mockito.doNothing().when(jobApplicationService).deleteJobApplication(any(UUID.class));

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/job-applications/" + this.firstJobApplication.getId())
            .contentType("application/json")
        )
        .andExpect(status().isNoContent());
    }

    @Test
    public void deleteJobApplicationThatDoesNotExist() throws Exception {
        mockMvc.perform(delete("/api/job-applications/" + this.firstJobApplication.getId())
            .contentType("application/json")
        )
        .andExpect(status().isNoContent());
    }

    @Test
    public void getAllJobApplications() throws Exception {
        Mockito.when(jobApplicationService.getAllJobApplications())
            .thenReturn(java.util.Arrays.asList(this.firstJobApplication, this.secondJobApplication));

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        );

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.secondJobApplication))
        );

        mockMvc.perform(get("/api/job-applications")
            .contentType("application/json")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getSpecificJobApplication() throws Exception {
        Mockito.when(jobApplicationService.getJobApplication(any(UUID.class)))
            .thenReturn(this.firstJobApplication);

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        );

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.secondJobApplication))
        );

        mockMvc.perform(get("/api/job-applications/" + this.firstJobApplication.getId())
            .contentType("application/json")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(this.firstJobApplication.getId().toString()));
    }

    @Test
    public void getJobApplicationThatDoesNotExist() throws Exception {
        Mockito.when(jobApplicationService.getJobApplication(any(UUID.class)))
            .thenReturn(null);

        mockMvc.perform(get("/api/job-applications/" + UUID.randomUUID())
            .contentType("application/json")
        )
        .andExpect(status().isNotFound());
    }

    @Test
    public void editJobApplication() throws Exception {
        Mockito.when(jobApplicationService.updateJobApplication(any(JobApplication.class)))
            .thenReturn(this.firstJobApplication);

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isCreated());

        this.firstJobApplication.setJobTitle("Senior Software Engineer");

        MvcResult result = mockMvc.perform(put("/api/job-applications/" + this.firstJobApplication.getId())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isOk())
        .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        JobApplication updatedJobApplication = objectMapper.readValue(responseContent, JobApplication.class);
        assertEquals(this.firstJobApplication.getId(), updatedJobApplication.getId());
        assertEquals(this.firstJobApplication.getJobTitle(), updatedJobApplication.getJobTitle());
    }

    @Test
    public void editJobApplicationWithInvalidData() throws Exception {
        Mockito.when(jobApplicationService.updateJobApplication(any(JobApplication.class)))
            .thenReturn(this.firstJobApplication);

        mockMvc.perform(post("/api/job-applications")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isCreated());

        this.firstJobApplication.setJobTitle(" ");

        mockMvc.perform(put("/api/job-applications/" + this.firstJobApplication.getId())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isBadRequest());
    }

    @Test
    public void editJobApplicationThatDoesNotExist() throws Exception {
        mockMvc.perform(put("/api/job-applications/" + this.firstJobApplication.getId())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(this.firstJobApplication))
        )
        .andExpect(status().isNotFound());
    }
}