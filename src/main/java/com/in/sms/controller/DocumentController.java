package com.in.sms.controller;

import com.in.sms.model.Document;
import com.in.sms.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/document")
@Tag(name = "Document APIs", description = "Create,Read")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    @Operation(summary = "Upload new Document")
    public ResponseEntity<?> saveDocument(MultipartFile file) throws IOException {
        try {
            Document saved = documentService.saveDocument(file);
            return ResponseEntity.ok("file uploaded with id: " + saved.getId());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File not uploaded due to error");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Document by Id")
    public ResponseEntity<byte[]> getDocument(@PathVariable Long id) {
        Document doc = documentService.getDocument(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getFileType()))
                .body(doc.getDoc());
    }
}


