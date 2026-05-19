package com.jobtracker.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobtracker.models.entities.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
}
