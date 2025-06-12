package com.in.sms.service;

import com.in.sms.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject saveSubject(Subject subject);
    Subject getSubjectById(Long id);
    List<Subject> getAllSubjects();
    Subject updateSubject(Long id, Subject updatedSubject);
    void deleteSubject(Long id);
}
