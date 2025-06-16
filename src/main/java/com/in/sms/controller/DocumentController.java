package com.in.sms.controller;

import com.in.sms.model.Document;
import com.in.sms.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/document")
@Tag(name = "Document APIs", description = "Create, Read")
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @PostMapping
    @Operation(summary = "Upload new Document")
    public ResponseEntity<?> saveDocument(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Request to upload file: originalFilename={}, size={} bytes", file.getOriginalFilename(), file.getSize());
        try {
            Document saved = documentService.saveDocument(file);
            logger.info("File uploaded successfully with ID: {}", saved.getId());
            return ResponseEntity.ok("file uploaded with id: " + saved.getId());
        } catch (Exception ex) {
            logger.error("File upload failed: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File not uploaded due to error");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Document by Id")
    public ResponseEntity<byte[]> getDocument(@PathVariable Long id) {
        logger.info("Request to fetch document with ID: {}", id);
        Document doc = documentService.getDocument(id);
        if (doc != null) {
            logger.info("Document found with ID: {} and file type: {}", doc.getId(), doc.getFileType());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(doc.getFileType()))
                    .body(doc.getDoc());
        } else {
            logger.warn("Document with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}
