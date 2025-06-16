package com.in.sms.controller;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.model.Semester;
import com.in.sms.service.SemesterService;
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
@RequestMapping("/api/semester")
@Tag(name = "Semester APIs", description = "Read, Update and Delete")
public class SemesterController {

    private static final Logger logger = LoggerFactory.getLogger(SemesterController.class);

    @Autowired
    private SemesterService semesterService;

    @PostMapping
    @Operation(summary = "Save new Semester")
    public ResponseEntity<SemesterResponseDto> saveSemester(@RequestBody Semester semester) {
        logger.info("Request to create semester: {}", semester);
        SemesterResponseDto savedSemester = semesterService.saveSemester(semester);
        logger.info("Semester created successfully with ID: {}", savedSemester.getId());
        return new ResponseEntity<>(savedSemester, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Semester by Id")
    public ResponseEntity<SemesterResponseDto> getSemesterById(@PathVariable Long id) {
        logger.info("Request to fetch semester with ID: {}", id);
        SemesterResponseDto semester = semesterService.getSemesterById(id);
        if (semester != null) {
            logger.info("Semester found with ID: {} and name: {}", semester.getId(), semester.getName());
            return new ResponseEntity<>(semester, HttpStatus.OK);
        } else {
            logger.warn("Semester with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get Semester by Name")
    public ResponseEntity<SemesterResponseDto> getSemesterByName(@RequestParam String name) {
        logger.info("Request to fetch semester with name: {}", name);
        SemesterResponseDto semester = semesterService.getSemesterByName(name);
        if (semester != null) {
            logger.info("Semester found with ID: {} and name: {}", semester.getId(), semester.getName());
        } else {
            logger.warn("Semester with name '{}' not found", name);
        }
        return new ResponseEntity<>(semester, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all Semesters")
    public ResponseEntity<List<SemesterResponseDto>> getAllSemesters() {
        logger.info("Request to fetch all semesters");
        List<SemesterResponseDto> semesters = semesterService.getAllSemester();
        logger.info("Total semesters found: {}", semesters.size());
        return new ResponseEntity<>(semesters, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Semester by Id")
    public ResponseEntity<SemesterResponseDto> updateSemester(@PathVariable Long id, @RequestBody Semester updatedSemester) {
        logger.info("Request to update semester with ID: {} | New data: {}", id, updatedSemester);
        SemesterResponseDto updated = semesterService.updateSemester(id, updatedSemester);
        if (updated != null) {
            logger.info("Semester updated successfully: {}", updated);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            logger.warn("Semester with ID {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Semester by Id")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        logger.info("Request to delete semester with ID: {}", id);
        semesterService.deleteSemester(id);
        logger.info("Semester with ID {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
