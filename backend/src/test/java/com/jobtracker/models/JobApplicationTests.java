package com.jobtracker.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jobtracker.models.entities.Company;
import com.jobtracker.models.entities.JobApplication;
import com.jobtracker.models.enums.StatusType;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class JobApplicationTests {
    private Validator validator;
    private JobApplication jobApplication;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        Company testCompany = new Company(
            UUID.randomUUID(),
            "Test Company",
            "https://testcompany.com",
            "Technology",
            "A great company to work for",
            500,
            true
        );

        this.jobApplication = new JobApplication(
            UUID.randomUUID(),
            "Software Engineer",
            "New York",
            "Full-time",
            "https://example.com/apply",
            60000L,
            120000L,
            LocalDate.now(),
            LocalDate.now().plusMonths(1),
            StatusType.APPLIED,
            "LinkedIn",
            testCompany
        );
    }

    @Test
    public void testJobApplicationValidFields() {    
        var violations = validator.validate(jobApplication);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testJobApplicationInvalidFields() {
        this.jobApplication.setJobTitle("");
        var violations = validator.validate(jobApplication);
        assertFalse(violations.isEmpty());
        
        this.jobApplication.setJobTitle("Software Engineer");
        this.jobApplication.setMinimumSalary(-50000L);
        violations = validator.validate(jobApplication);
        assertFalse(violations.isEmpty());

        this.jobApplication.setMinimumSalary(60000L);
        this.jobApplication.setMaximumSalary(-100000L);
        violations = validator.validate(jobApplication);
        assertFalse(violations.isEmpty());

        this.jobApplication.setMaximumSalary(120000L);
        this.jobApplication.setStatus(null);
        violations = validator.validate(jobApplication);
        assertFalse(violations.isEmpty());
    }
}
