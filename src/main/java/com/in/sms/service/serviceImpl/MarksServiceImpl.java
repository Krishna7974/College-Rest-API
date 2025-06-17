package com.in.sms.service.serviceImpl;

import com.in.sms.model.Marks;
import com.in.sms.repository.MarksRepository;
import com.in.sms.service.serviceInterfaces.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksServiceImpl implements MarksService {

    @Autowired
    private MarksRepository marksRepository;

    @Override
    public Marks saveMarks(Marks marks) {
        return marksRepository.save(marks);
    }

    @Override
    public Marks getMarksById(Long id) {
        return marksRepository.findById(id).orElse(null);
    }

    @Override
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    @Override
    public List<Marks> getMarksByStudentId(Long studentId) {
//        return marksRepository.findByStudentId(studentId);
        return null;
    }

    @Override
    public List<Marks> getMarksBySubjectId(Long subjectId) {
//        return marksRepository.findBySubjectId(subjectId);
         return null;
    }

    @Override
    public Marks updateMarks(Long id, Marks updatedMarks) {
        if (marksRepository.existsById(id)) {
            updatedMarks.setId(id);
            return marksRepository.save(updatedMarks);
        }
        return null;
    }

    @Override
    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
}

