package com.jobtracker.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.models.JobApplication;
import com.jobtracker.services.JobApplicationService;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }
    
    @PostMapping
    public JobApplication createJobApplication(@RequestBody JobApplication jobApplication){
        return jobApplicationService.createJobApplication(jobApplication);
    }
    
    @DeleteMapping("/{id}")
    public void deleteJobApplication(@PathVariable UUID id){
        jobApplicationService.deleteJobApplication(id);
    }

    @GetMapping
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationService.getAllJobApplications();
    }

    @GetMapping("/{id}")
    public JobApplication getJobApplication(@PathVariable UUID id) {
        return jobApplicationService.getJobApplication(id);
    }

    @PutMapping("/{id}")
    public JobApplication updateJobApplication(@PathVariable UUID id, @RequestBody JobApplication jobApplication) {
        jobApplication.setId(id);
        return jobApplicationService.updateJobApplication(jobApplication);
    }
}
