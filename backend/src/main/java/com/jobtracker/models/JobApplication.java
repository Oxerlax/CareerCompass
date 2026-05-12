package com.jobtracker.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String jobTitle;
    private String location;
    private String jobType;
    private String applicationLink;
    private double minimumSalary;
    private double maximumSalary;
    private LocalDate dateApplied;
    private LocalDate deadline;
    private StatusType status;
    private String source;
}
