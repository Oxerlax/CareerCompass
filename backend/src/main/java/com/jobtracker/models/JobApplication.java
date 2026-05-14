package com.jobtracker.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String jobTitle;
    private String location;
    private String jobType;
    private String applicationLink;
    @Min(1)
    private Long minimumSalary;
    @Min(1)
    private Long maximumSalary;
    private LocalDate dateApplied;
    private LocalDate deadline;
    @NotNull
    private StatusType status;
    @NotBlank
    private String source;
}
