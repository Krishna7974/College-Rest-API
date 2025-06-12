package com.in.sms.controller;

import com.in.sms.dto.StudentSearchDto;
import com.in.sms.model.Student;
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
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        String className = student.getSemester().getName();

        studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Student by Id")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student std = studentService.getStudentById(id);
        return new ResponseEntity<>(std, HttpStatus.FOUND);
    }

    @GetMapping
    @Operation(summary = "Get all Student")
    public ResponseEntity<?> getAllStudent() {
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update Student by Id")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student std = studentService.updateStudent(id, student);
        return new ResponseEntity<>(std, HttpStatus.OK);
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

