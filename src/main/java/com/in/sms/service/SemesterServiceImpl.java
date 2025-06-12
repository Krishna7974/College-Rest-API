package com.in.sms.service;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.model.Semester;
import com.in.sms.model.Subject;
import com.in.sms.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public SemesterResponseDto saveSemester(Semester semester) {
        Optional<Semester> classRoom1 = semesterRepository.getSemesterByName(semester.getName());
        if (classRoom1.isEmpty()) {
            return mapToResponseDto(semesterRepository.save(semester));
        }else throw new RuntimeException("class room "+ semester.getName()+" is already exists");
    }

    @Override
    public SemesterResponseDto getSemesterById(Long id) {
        Optional<Semester> optional=semesterRepository.findById(id);
        if (optional.isPresent()){
            return mapToResponseDto(optional.get());
        }else throw new RuntimeException("Semester not found for id: "+id);
    }

    @Override
    public SemesterResponseDto getSemesterByName(String sem) {
        Optional<Semester> optional=semesterRepository.getSemesterByName(sem);
        if (optional.isPresent()){
            return mapToResponseDto(optional.get());
        }else throw new RuntimeException("semester not found for this name: "+sem);

    }


    @Override
    public List<SemesterResponseDto> getAllSemester() {
        List<Semester> semesterList=semesterRepository.findAll();
        List<SemesterResponseDto> semesterResponseDtoList=new ArrayList<>();
        for(Semester s:semesterList){
            semesterResponseDtoList.add(mapToResponseDto(s));
        }
        return semesterResponseDtoList;
    }

    @Override
    public SemesterResponseDto updateSemester(Long id, Semester updatedSemester) {
        if (semesterRepository.existsById(id)) {
            updatedSemester.setId(id);
            return mapToResponseDto(semesterRepository.save(updatedSemester));
        }
        return null;
    }

    @Override
    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }

    public SemesterResponseDto mapToResponseDto(Semester semester){
        SemesterResponseDto semesterResponseDto=new SemesterResponseDto();
        semesterResponseDto.setId(semester.getId());
        semesterResponseDto.setName(semester.getName());
        semesterResponseDto.setNumberOfStudent(semester.getStudent().size());
        semesterResponseDto.setListOfSubjects(objectToStringList(semester.getSubjects()));
        return semesterResponseDto;
    }

    public List<String> objectToStringList(List<Subject> subjects){
        List<String> list=new ArrayList<>();
        for (Subject s:subjects){
            list.add(s.getName());
        }
        return list;
    }

}
