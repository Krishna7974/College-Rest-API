package com.in.sms.controller;

import com.in.sms.dto.student.StudentRequestDto;
import com.in.sms.dto.student.StudentResponseDto;
import com.in.sms.dto.student.StudentSearchDto;
import com.in.sms.service.StudentService;
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
@RequestMapping("/api/student")
@Tag(name = "Student APIs", description = "Read,Update,Search and Delete")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping
    @Operation(summary = "Save new Student")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentRequestDto student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Student by Id")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.FOUND);
    }

    @GetMapping
    @Operation(summary = "Get all Student")
    public ResponseEntity<List<StudentResponseDto>> getAllStudent() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update Student by Id")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto student) {
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Student by Id")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("student deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/search")
    @Operation(summary = "Search Student by Name/Sem/Branch/Category/Address/RollNo/Email")
    public ResponseEntity<?> searchStudent(@RequestBody StudentSearchDto studentSearchDto,
                                              @RequestParam(defaultValue = "0", required = false) int pageNo,
                                              @RequestParam(defaultValue = "5", required = false) int pageSize,
                                              @RequestParam(defaultValue = "asc", required = false) String sortDir,
                                              @RequestParam(defaultValue = "rollNo", required = false) String sortBy) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return ResponseEntity.ok(studentService.searchStudent(studentSearchDto, pageable).getContent());
    }
}

