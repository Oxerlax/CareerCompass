package com.jobtracker.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobtracker.exceptions.ResourceNotFoundException;
import com.jobtracker.models.entities.JobApplication;
import com.jobtracker.repositories.JobApplicationRepository;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTests {
    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private JobApplicationService service;

    @Test
    void getJobApplication_WhenFound_ReturnsApplication() {
        UUID id = UUID.randomUUID();
        JobApplication expected = new JobApplication();
        expected.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        JobApplication result = service.getJobApplication(id);

        assertEquals(expected, result);
    }

    @Test
    void getJobApplication_WhenNotFound_ThrowsResourceNotFoundException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
            () -> service.getJobApplication(id));
    }

    @Test
    void updateJobApplication_WhenFound_SavesAndReturnsApplication() {
        JobApplication app = new JobApplication();
        app.setId(UUID.randomUUID());

        when(repository.existsById(app.getId())).thenReturn(true);
        when(repository.save(app)).thenReturn(app);

        JobApplication result = service.updateJobApplication(app);

        assertEquals(app, result);
        verify(repository).save(app);
    }

    @Test
    void updateJobApplication_WhenNotFound_ThrowsResourceNotFoundException() {
        JobApplication app = new JobApplication();
        app.setId(UUID.randomUUID());
        
        when(repository.existsById(app.getId())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
            () -> service.updateJobApplication(app));
    }
}
