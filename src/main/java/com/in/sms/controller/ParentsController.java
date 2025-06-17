package com.in.sms.controller;

import com.in.sms.model.Parents;
import com.in.sms.service.serviceInterfaces.ParentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@Tag(name = "Parents APIs", description = "Read, Update and Delete")
public class ParentsController {

    private static final Logger logger = LoggerFactory.getLogger(ParentsController.class);

    @Autowired
    private ParentsService parentsService;

    @PostMapping
    @Operation(summary = "Save new Parent")
    public ResponseEntity<Parents> createParents(@RequestBody Parents parents) {
        logger.info("Request to create parent: {}", parents);
        Parents saved = parentsService.saveParents(parents);
        logger.info("Parent created successfully with ID: {}", saved.getId());
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    @Operation(summary = "Get all Parents")
    public ResponseEntity<List<Parents>> getAllParents() {
        logger.info("Request to fetch all parents");
        List<Parents> parentsList = parentsService.getAllParents();
        logger.info("Total parents found: {}", parentsList.size());
        return ResponseEntity.ok(parentsList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Parents by Id")
    public ResponseEntity<Parents> getParentsById(@PathVariable Long id) {
        logger.info("Request to fetch parents with ID: {}", id);
        Parents parent = parentsService.getParentsById(id);
        if (parent != null) {
            logger.info("Parents found with ID: {} and fatherName: {}", parent.getId(), parent.getFatherName());
            return ResponseEntity.ok(parent);
        } else {
            logger.warn("Parents with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Parents by Id")
    public ResponseEntity<Parents> updateParents(@PathVariable Long id, @RequestBody Parents parents) {
        logger.info("Request to update parent with ID: {} | New Data: {}", id, parents);
        Parents updated = parentsService.updateParents(id, parents);
        if (updated != null) {
            logger.info("Parent updated successfully: {}", updated);
            return ResponseEntity.ok(updated);
        } else {
            logger.warn("Parent with ID {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Parents by Id")
    public ResponseEntity<Void> deleteParents(@PathVariable Long id) {
        logger.info("Request to delete parent with ID: {}", id);
        parentsService.deleteParents(id);
        logger.info("Parent with ID {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
