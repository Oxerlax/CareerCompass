package com.jobtracker.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jobtracker.exceptions.ResourceNotFoundException;
import com.jobtracker.models.entities.JobApplication;
import com.jobtracker.repositories.JobApplicationRepository;

@Service
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplication createJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public void deleteJobApplication(UUID id) {
        jobApplicationRepository.deleteById(id);
    }

    public java.util.List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication getJobApplication(UUID id) {
        return jobApplicationRepository.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Job application not found"));
    }

    public JobApplication updateJobApplication(JobApplication jobApplication) {
        if (!jobApplicationRepository.existsById(jobApplication.getId())) {
            throw new ResourceNotFoundException("Job application not found");
        }
        
        return jobApplicationRepository.save(jobApplication);
    }
}
