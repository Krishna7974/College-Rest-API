package com.in.sms.controller;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.model.Semester;
import com.in.sms.service.SemesterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/semester")
@Tag(name = "Semester APIs", description = "Read,Update and Delete")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;


    @PostMapping
    @Operation(summary = "Save new Semester")
    public ResponseEntity<SemesterResponseDto> saveSemester(@RequestBody Semester semester) {
        SemesterResponseDto savedSemester = semesterService.saveSemester(semester);
        return new ResponseEntity<>(savedSemester, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Semester by Id")
    public ResponseEntity<SemesterResponseDto> getSemesterById(@PathVariable Long id) {
        SemesterResponseDto semester = semesterService.getSemesterById(id);
        if (semester != null) {
            return new ResponseEntity<>(semester, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get Semester by Name")
    public ResponseEntity<SemesterResponseDto> getSemesterByName(@RequestParam String name) {
        SemesterResponseDto semester = semesterService.getSemesterByName(name);
        return new ResponseEntity<>(semester, HttpStatus.OK);

    }


    @GetMapping
    @Operation(summary = "Get all Semesters")
    public ResponseEntity<List<SemesterResponseDto>> getAllSemesters() {
        List<SemesterResponseDto> semesters = semesterService.getAllSemester();
        return new ResponseEntity<>(semesters, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Semester by Id")
    public ResponseEntity<SemesterResponseDto> updateSemester(@PathVariable Long id, @RequestBody Semester updatedSemester) {
        SemesterResponseDto updated = semesterService.updateSemester(id, updatedSemester);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Semester by Id")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemester(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

