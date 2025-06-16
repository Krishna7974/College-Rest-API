package com.in.sms.controller;

import com.in.sms.model.Subject;
import com.in.sms.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@Tag(name = "Subject APIs", description = "Read, Update and Delete")
public class SubjectController {

    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    @Operation(summary = "Save new Subject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        logger.info("Request to create subject: {}", subject);
        Subject savedSubject = subjectService.saveSubject(subject);
        logger.info("Subject created successfully with ID: {}", savedSubject.getId());
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Subject by Id")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        logger.info("Request to fetch subject with ID: {}", id);
        Subject subject = subjectService.getSubjectById(id);
        if (subject != null) {
            logger.info("Subject found with ID: {} and name: {}", subject.getId(), subject.getName());
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } else {
            logger.warn("Subject with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        logger.info("Request to fetch all subjects");
        List<Subject> subjects = subjectService.getAllSubjects();
        logger.info("Total subjects found: {}", subjects.size());
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Subject by Id")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        logger.info("Request to update subject with ID: {} | New data: {}", id, subject);
        Subject updated = subjectService.updateSubject(id, subject);
        if (updated != null) {
            logger.info("Subject updated successfully: {}", updated);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            logger.warn("Subject with ID {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Subject by Id")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        logger.info("Request to delete subject with ID: {}", id);
        subjectService.deleteSubject(id);
        logger.info("Subject with ID {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
