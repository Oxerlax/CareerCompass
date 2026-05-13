package com.jobtracker.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import com.jobtracker.models.JobApplication;
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
                .orElse(null);
    }

    public JobApplication updateJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }
}
