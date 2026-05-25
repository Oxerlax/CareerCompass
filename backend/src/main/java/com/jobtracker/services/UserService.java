package com.jobtracker.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jobtracker.exceptions.ResourceNotFoundException;
import com.jobtracker.models.entities.Company;
import com.jobtracker.models.entities.Contact;
import com.jobtracker.models.entities.Document;
import com.jobtracker.models.entities.JobApplication;
import com.jobtracker.models.entities.User;
import com.jobtracker.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException("User not found"));
    }

    public List<JobApplication> getUserJobApplications(UUID id) {
        User user = getUserById(id);
        return user.getJobApplications();
    }

    public List<Company> getUserCompanies(UUID id) {
        User user = getUserById(id);
        return user.getCompanies();
    }

    public List<Contact> getUserContacts(UUID id) {
        User user = getUserById(id);
        return user.getContacts();
    }

    public List<Document> getUserDocuments(UUID id) {
        User user = getUserById(id);
        return user.getDocuments();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, User user) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        
        user.setId(id);
        return userRepository.save(user);
    }
}
