package com.in.sms.controller;

import com.in.sms.dto.teacher.TeacherRequestDto;
import com.in.sms.dto.teacher.TeacherResponseDto;
import com.in.sms.dto.teacher.TeacherSearchDto;
import com.in.sms.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@Tag(name = "Teacher APIs", description = "Read,Update,Search and Delete")
public class TeacherController {

    private final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    @Operation(summary = "Save new Teacher")
    public ResponseEntity<TeacherResponseDto> saveTeacher(@RequestBody TeacherRequestDto teacher) {
        logger.info("Received request to save new teacher: {}", teacher);
        TeacherResponseDto response = teacherService.saveTeacher(teacher);
        logger.info("Teacher saved successfully: {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Teacher by Id")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        logger.info("Received request to fetch teacher with ID: {}", id);
        TeacherResponseDto teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            logger.info("Teacher found: {}", teacher);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            logger.warn("Teacher with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Teachers")
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        logger.info("Received request to fetch all teachers");
        List<TeacherResponseDto> teacherList = teacherService.getAllTeachers();
        logger.info("Total teachers found: {}", teacherList.size());
        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Teacher by Id")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDto updatedTeacher) {
        logger.info("Received request to update teacher with ID: {} | New data: {}", id, updatedTeacher);
        TeacherResponseDto updated = teacherService.updateTeacher(id, updatedTeacher);
        if (updated != null) {
            logger.info("Teacher updated successfully: {}", updated);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            logger.warn("Teacher with ID {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Teacher by Id")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        logger.info("Received request to delete teacher with ID: {}", id);
        teacherService.deleteTeacher(id);
        logger.info("Teacher with ID {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    @Operation(summary = "Search Teacher by Name/Email/Subject")
    public ResponseEntity<List<TeacherResponseDto>> searchTeacher(@RequestBody TeacherSearchDto dto,
                                                                  @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                                  @RequestParam(defaultValue = "5", required = false) int pageSize,
                                                                  @RequestParam(defaultValue = "asc", required = false) String sortDir,
                                                                  @RequestParam(defaultValue = "name", required = false) String sortBy) {

        logger.info("Received request to search teacher with criteria: {} | pageNo: {}, pageSize: {}, sortBy: {}, sortDir: {}",
                dto, pageNo, pageSize, sortBy, sortDir);

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<TeacherResponseDto> result = teacherService.searchTeacher(dto, pageable);
        logger.info("Search result returned {} teacher(s)", result.size());
        return ResponseEntity.ok(result);
    }
}
