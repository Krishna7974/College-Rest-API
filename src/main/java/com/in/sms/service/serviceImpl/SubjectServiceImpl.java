package com.in.sms.service.serviceImpl;

import com.in.sms.model.Subject;
import com.in.sms.repository.SubjectRepository;
import com.in.sms.service.serviceInterfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAllSubjectOrderByCode();
    }

    @Override
    public Subject updateSubject(Long id, Subject updatedSubject) {
        if (subjectRepository.existsById(id)) {
            updatedSubject.setId(id);
            return subjectRepository.save(updatedSubject);
        }
        return null;
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
