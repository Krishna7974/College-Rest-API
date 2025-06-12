package com.in.sms.service;

import com.in.sms.dto.TeacherRequestDto;
import com.in.sms.dto.TeacherResponseDto;
import com.in.sms.dto.TeacherSearchDto;
import com.in.sms.model.Subject;
import com.in.sms.model.Teacher;
import com.in.sms.repository.SubjectRepository;
import com.in.sms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public TeacherResponseDto saveTeacher(TeacherRequestDto teacher) {
        List<Subject> resolvedSubjects = new ArrayList<>();

        for (String s : teacher.getSubject()) {
            Optional<Subject> subjectOpt = subjectRepository.findByName(s);
            if (subjectOpt.isPresent()) {
                resolvedSubjects.add(subjectOpt.get());
            } else {
                throw new RuntimeException("Subject not found: " + s);
            }
        }
        Teacher t = new Teacher();
        t.setName(teacher.getName());
        t.setEmail(teacher.getEmail());
        t.setSubject(resolvedSubjects);
        Teacher teacher1 = teacherRepository.save(t);
        return mapToTeacherDto(teacher1);
    }

    public static TeacherResponseDto mapToTeacherDto(Teacher teacher) {
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setEmail(teacher.getEmail());
        teacherResponseDto.setName(teacher.getName());

        List<String> subjects = new ArrayList<>();
        for (Subject s : teacher.getSubject()) {
            subjects.add(s.getName());
        }

        teacherResponseDto.setSubject(subjects);
        return teacherResponseDto;
    }

    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return mapToTeacherDto(teacher.get());
        } else throw new RuntimeException("teacher not found for id " + id);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        List<TeacherResponseDto> teacherResponseDtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherResponseDtos.add(mapToTeacherDto(teacher));
        }
        return teacherResponseDtos;
    }

    @Override
    public TeacherResponseDto updateTeacher(Long id, Teacher updatedTeacher) {
        if (teacherRepository.existsById(id)) {
            updatedTeacher.setId(id);
            Teacher teacher = teacherRepository.save(updatedTeacher);
            return mapToTeacherDto(teacher);
        }
        return null;
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Page<Teacher> searchTeacher(TeacherSearchDto dto, Pageable pageable) {
        return teacherRepository.searchTeacher(dto,pageable);
    }
}
