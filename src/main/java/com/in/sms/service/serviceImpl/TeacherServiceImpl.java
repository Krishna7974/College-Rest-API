package com.in.sms.service.serviceImpl;

import com.in.sms.dto.teacher.TeacherRequestDto;
import com.in.sms.dto.teacher.TeacherResponseDto;
import com.in.sms.dto.teacher.TeacherSearchDto;
import com.in.sms.exception.CustomException;
import com.in.sms.model.Subject;
import com.in.sms.model.Teacher;
import com.in.sms.model.User;
import com.in.sms.repository.SubjectRepository;
import com.in.sms.repository.TeacherRepository;
import com.in.sms.repository.UserRepository;
import com.in.sms.service.serviceInterfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TeacherResponseDto saveTeacher(TeacherRequestDto teacher) {

        if(userRepository.existsByEmail(teacher.getEmail())) {
            throw new CustomException("User already exists with this email:  "+teacher.getEmail());
        }

        Teacher t=mapTeacherRequestToTeacher(teacher);

        User user =new User();
        user.setEmail(t.getEmail());
        user.setName(t.getName());
        user.setPassword(passwordEncoder.encode(teacher.getPassword()));
        user.setRole(t.getRoles().get(0));
        userRepository.save(user);

        t.setSubject(getSubjectList(teacher.getSubject()));
        t.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return mapTeacherToTeacherResponse(teacherRepository.save(t));
    }


    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return mapTeacherToTeacherResponse(teacher.get());
        } else throw new RuntimeException("teacher not found for id " + id);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(this::mapTeacherToTeacherResponse).toList();
    }

    @Override
    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto newTeacher) {
        Optional<Teacher> oldTeacher=teacherRepository.findById(id);
        if (oldTeacher.isPresent()) {
            Teacher t=mapTeacherRequestToTeacher(newTeacher);
            if (newTeacher.getPassword() != null) {
                t.setPassword(passwordEncoder.encode(newTeacher.getPassword()));
            } else {
                t.setPassword(oldTeacher.get().getPassword());
            }
            t.setSubject(getSubjectList(newTeacher.getSubject()));
            t.setId(id);
            return mapTeacherToTeacherResponse(teacherRepository.save(t));
        }
        else throw new RuntimeException("Teacher not found for id: "+id);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherResponseDto> searchTeacher(TeacherSearchDto dto, Pageable pageable) {
        Page<Teacher> teachersPage=teacherRepository.searchTeacher(dto,pageable);
        List<TeacherResponseDto> dtoList=teachersPage.stream().map(this::mapTeacherToTeacherResponse).toList();
        return dtoList;
    }

    public Teacher mapTeacherRequestToTeacher(TeacherRequestDto requestDto){
        Teacher teacher = new Teacher();
        teacher.setName(requestDto.getName());
        teacher.setEmail(requestDto.getEmail());
        teacher.setRoles(List.of("TEACHER"));
        return teacher;
    }

    public TeacherResponseDto mapTeacherToTeacherResponse(Teacher teacher) {
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setId(teacher.getId());
        teacherResponseDto.setName(teacher.getName());
        teacherResponseDto.setEmail(teacher.getEmail());
        List<String> list=teacher.getSubject().stream().map(Subject::getName).toList();
        teacherResponseDto.setSubject(list);
        return teacherResponseDto;
    }

    public List<Subject> getSubjectList(List<String> stringList){
        if(subjectRepository==null) System.out.println("hello");
        return stringList.stream().map(x->subjectRepository.findByName(x).orElseThrow(()->new RuntimeException("Given subjects are not exists: "+x))).toList();
    }
}
