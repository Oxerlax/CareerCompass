# CareerCompass

CareerCompass is a full stack career management platform designed to help students and job seekers organize every aspect of the internship and job search process.

Instead of relying on spreadsheets, notes, calendars, and multiple websites, CareerCompass provides a centralized application where users can track job applications, manage recruiter and networking contacts, store resume documents, and gain meaningful insights into their progress.

## Overview

The job search process can quickly become overwhelming, especially when applying to dozens or even hundreds of opportunities. CareerCompass simplifies this process by bringing all career related information into one cohesive system.

Users can record applications, monitor status updates, manage company information, upload resumes, and maintain professional contacts, all within a modern and intuitive web application.

## Features

### Job Application Tracking

Users can create and manage applications, record application dates, track deadlines, monitor salary ranges, store job posting links, and update statuses such as Applied, Interviewing, Offer, and Rejected.

### Company Management

Applications are associated with dedicated company records, allowing users to maintain company specific details such as website, industry, headquarters, and notes.

### Contact Management

Users can store recruiter, engineer, alumnus, and mentor information, including email addresses, LinkedIn profiles, follow up dates, and communication notes.

### Document Management

Users can upload and organize important files such as resumes and cover letters. The initial version focuses on PDF support, ensuring a consistent and professional format.

### Authentication and Security

Each user has a secure account protected through encrypted password hashing using BCrypt via Spring Security.

### Future Features

Planned enhancements include interview scheduling, reminders, analytics dashboards, and AI powered recommendations.

## Technology Stack

### Frontend

Angular and TypeScript

### Backend

Spring Boot, Spring Web, Spring Data JPA, Spring Security, and JWT Authentication

### Database

PostgreSQL

### Build and Development Tools

Maven, Git, GitHub, Postman

### Future Deployment

Docker, Amazon Web Services, GitHub Actions

## Core Entities

### User

Represents a registered account and serves as the central owner of all data, including job applications, companies, contacts, and documents.

### JobApplication

Represents an individual internship, co op, or full time application, including job details, deadlines, salary information, and status.

### Company

Represents an employer and stores reusable company information shared across multiple applications and contacts.

### Contact

Represents recruiters, hiring managers, alumni, mentors, and other professional connections.

### Document

Represents uploaded files such as resumes and cover letters, along with file metadata and storage information.

## Architecture

CareerCompass follows a layered full stack architecture.

Angular provides the frontend user interface and communicates with a RESTful API.

Spring Boot handles business logic, authentication, validation, and persistence.

PostgreSQL stores normalized relational data.

## Project Goals

This project is intended to demonstrate practical software engineering skills, including object oriented design, REST API development, relational database modeling, authentication and security, file upload handling, and full stack integration.

It also addresses a real world problem that is highly relevant to students and professionals, making it both technical and personally useful for everyone!

## Planned Enhancements

Interview scheduling, reminder notifications, analytics dashboards, resume matching, follow up email generation, and AI powered career insights.

## Project Cloning Instructions

To be developed at a later date once this project has enough development.

## Current Development Status

CareerCompass is currently in the early backend development phase, with the core data model and foundational architecture already designed and partially implemented.

### Completed Work

The following backend entities have been created and structured using Spring Boot and JPA:

User, JobApplication, Company, Contact, Document

These entities establish the core relational model of the application and define how users will interact with job applications, companies, and professional contacts.

Basic database connectivity has been configured using PostgreSQL, and Spring Boot is successfully connecting to the database environment. Initial JPA configuration has been validated, including entity detection and schema generation setup.

Core relationships between entities have been conceptually defined, including ownership of applications, contacts, and documents by a user, as well as associations between applications and companies.

### In Progress

The current focus of development includes refining entity relationships, improving database design, and preparing the backend for full REST API implementation.

User authentication design is being planned, including secure password storage and integration with Spring Security.

Service and repository layers are being structured to support clean separation of concerns and scalable backend architecture.

### Upcoming Work

The next stage of development will include implementation of REST controllers for core entities.

JWT based authentication will be introduced to secure API endpoints.

Frontend development with Angular will begin once core backend functionality is stable.

Document upload handling will be implemented with local storage support, followed later by cloud storage integration.

Reminder functionality and analytics features will be developed in later phases.

### Overall Progress

The project currently represents a strong foundational backend architecture with core domain modeling complete and ready for expansion into full stack development. The next milestone is building out functional APIs and connecting the Angular frontend to the Spring Boot backend.

## Author

Developed by Kris Bali as a personal full stack software engineering project to streamline the internship and job search experience.
