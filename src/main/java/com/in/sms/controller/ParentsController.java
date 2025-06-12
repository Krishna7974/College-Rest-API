package com.in.sms.controller;

import com.in.sms.model.Parents;
import com.in.sms.service.ParentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@Tag(name = "Parents APIs", description = "Read,Update and Delete")
public class ParentsController {

    @Autowired
    private ParentsService parentsService;

    @PostMapping
    @Operation(summary = "Save new Parent")
    public ResponseEntity<Parents> createParents(@RequestBody Parents parents) {
        return ResponseEntity.ok(parentsService.saveParents(parents));
    }

    @GetMapping
    @Operation(summary = "Get all Parents")
    public ResponseEntity<List<Parents>> getAllParents() {
        return ResponseEntity.ok(parentsService.getAllParents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Parents by Id")
    public ResponseEntity<Parents> getParentsById(@PathVariable Long id) {
        return ResponseEntity.ok(parentsService.getParentsById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Parents by Id")
    public ResponseEntity<Parents> updateParents(@PathVariable Long id, @RequestBody Parents parents) {
        return ResponseEntity.ok(parentsService.updateParents(id, parents));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Parents by Id")
    public ResponseEntity<Void> deleteParents(@PathVariable Long id) {
        parentsService.deleteParents(id);
        return ResponseEntity.noContent().build();
    }
}