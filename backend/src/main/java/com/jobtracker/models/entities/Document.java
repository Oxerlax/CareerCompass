package com.jobtracker.models.entities;

import java.time.Instant;
import java.util.UUID;

import com.jobtracker.models.enums.DocumentType;
import com.jobtracker.models.enums.MimeType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private UUID userId;
    @NotBlank
    private String name;
    @NotNull
    private DocumentType documentType;
    @NotBlank
    private String originalFileName;
    @NotBlank
    private String storedFileName;
    @NotBlank
    private String filePath;
    @NotNull
    private Long fileSizeBytes;
    @NotNull
    private Instant uploadDate;
    @Enumerated(EnumType.STRING)
    @NotNull
    private MimeType mimeType;
}
