package com.jobtracker.models.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String displayName;
    @NotBlank
    private String passwordHash;
    private String profession;
    @NotNull
    private Instant createdAt;
    @NotNull
    private Instant updatedAt;
    @NotNull
    private Instant lastLoginAt;
    @OneToMany
    private List<JobApplication> jobApplications;
    @OneToMany
    private List<Company> companies;
    @OneToMany
    private List<Contact> contacts;
    @OneToMany
    private List<Document> documents;
}
