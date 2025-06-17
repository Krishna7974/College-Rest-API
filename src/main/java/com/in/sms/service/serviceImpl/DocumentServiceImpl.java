package com.in.sms.service.serviceImpl;

import com.in.sms.model.Document;
import com.in.sms.repository.DocumentRepository;
import com.in.sms.service.serviceInterfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document saveDocument(MultipartFile file) throws IOException {
        Document doc=new Document();
        doc.setName(file.getOriginalFilename());
        doc.setFileType(file.getContentType());
        doc.setDoc(file.getBytes());
        return documentRepository.save(doc);
    }

    @Override
    public Document getDocument(Long id) {
        return documentRepository.findById(id).orElseThrow(()->new RuntimeException("document not found"));
    }
}
