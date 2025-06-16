package com.in.sms.controller;

import com.in.sms.dto.student.StudentRequestDto;
import com.in.sms.dto.student.StudentResponseDto;
import com.in.sms.dto.student.StudentSearchDto;
import com.in.sms.service.StudentService;
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
@RequestMapping("/api/student")
@Tag(name = "Student APIs", description = "Read,Update,Search and Delete")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Save new Student")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentRequestDto student) {
        logger.info("Received request to save new student: {}", student);
        StudentResponseDto savedStudent = studentService.saveStudent(student);
        logger.info("Student saved successfully: {}", savedStudent);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Student by Id")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        logger.info("Received request to fetch student with ID: {}", id);
        StudentResponseDto student = studentService.getStudentById(id);
        if (student != null) {
            logger.info("Student found with ID: {} and rollNo: {}", student.getId(), student.getRollNo());
            return new ResponseEntity<>(student, HttpStatus.FOUND);
        } else {
            logger.warn("Student with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Student")
    public ResponseEntity<List<StudentResponseDto>> getAllStudent() {
        logger.info("Received request to fetch all students");
        List<StudentResponseDto> students = studentService.getAllStudents();
        logger.info("Total students found: {}", students.size());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update Student by Id")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto student) {
        logger.info("Received request to update student with ID: {} | New data: {}", id, student);
        StudentResponseDto updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            logger.info("Student updated successfully: {}", updatedStudent);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            logger.warn("Student with ID {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Student by Id")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        logger.info("Received request to delete student with ID: {}", id);
        studentService.deleteStudent(id);
        logger.info("Student with ID {} deleted successfully", id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/search")
    @Operation(summary = "Search Student by Name/Sem/Branch/Category/Address/RollNo/Email")
    public ResponseEntity<?> searchStudent(@RequestBody StudentSearchDto studentSearchDto,
                                           @RequestParam(defaultValue = "0", required = false) int pageNo,
                                           @RequestParam(defaultValue = "5", required = false) int pageSize,
                                           @RequestParam(defaultValue = "asc", required = false) String sortDir,
                                           @RequestParam(defaultValue = "rollNo", required = false) String sortBy) {
        logger.info("Received request to search students with filters: {} | pageNo: {}, pageSize: {}, sortBy: {}, sortDir: {}",
                studentSearchDto, pageNo, pageSize, sortBy, sortDir);

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<StudentResponseDto> results = studentService.searchStudent(studentSearchDto, pageable).getContent();
        logger.info("Search returned {} student(s)", results.size());
        return ResponseEntity.ok(results);
    }
}
