package com.in.sms.service;

import com.in.sms.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    Document saveDocument(MultipartFile file) throws IOException;
    Document getDocument(Long id);
}
