package com.in.sms.controller;

import com.in.sms.dto.teacher.TeacherRequestDto;
import com.in.sms.dto.teacher.TeacherResponseDto;
import com.in.sms.dto.teacher.TeacherSearchDto;
import com.in.sms.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    @Operation(summary = "Save new Teacher")
    public ResponseEntity<TeacherResponseDto> saveTeacher(@RequestBody TeacherRequestDto teacher) {
        return new ResponseEntity<>(teacherService.saveTeacher(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Teacher by Id")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        TeacherResponseDto teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Teachers")
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Teacher by Id")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDto updatedTeacher) {
        TeacherResponseDto teacher = teacherService.updateTeacher(id, updatedTeacher);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Teacher by Id")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    @Operation(summary = "Search Teacher by Name/Email/Subject")
    public ResponseEntity<?> searchTeacher(@RequestBody TeacherSearchDto dto,
                                           @RequestParam(defaultValue = "0", required = false) int pageNo,
                                           @RequestParam(defaultValue = "5", required = false) int pageSize,
                                           @RequestParam(defaultValue = "asc", required = false) String sortDir,
                                           @RequestParam(defaultValue = "name", required = false) String sortBy) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return ResponseEntity.ok(teacherService.searchTeacher(dto, pageable).getContent());
    }
}