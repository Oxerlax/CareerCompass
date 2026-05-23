package com.jobtracker.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jobtracker.models.entities.Company;
import com.jobtracker.models.entities.Contact;
import com.jobtracker.models.entities.Document;
import com.jobtracker.models.entities.JobApplication;
import com.jobtracker.models.entities.User;
import com.jobtracker.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/{id}/job-applications")
    public ResponseEntity<List<JobApplication>> getUserJobApplications(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserJobApplications(id));
    }

    @GetMapping("/{id}/companies")
    public ResponseEntity<List<Company>> getUserCompanies(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserCompanies(id));
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<Contact>> getUserContacts(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserContacts(id));
    }

    @GetMapping("/{id}/documents")
    public ResponseEntity<List<Document>> getUserDocuments(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserDocuments(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
