package com.in.sms.service.serviceInterfaces;

import com.in.sms.model.Marks;

import java.util.List;

public interface MarksService {

    Marks saveMarks(Marks marks);
    Marks getMarksById(Long id);
    List<Marks> getAllMarks();
    List<Marks> getMarksByStudentId(Long studentId);
    List<Marks> getMarksBySubjectId(Long subjectId);
    Marks updateMarks(Long id, Marks updatedMarks);
    void deleteMarks(Long id);

}
