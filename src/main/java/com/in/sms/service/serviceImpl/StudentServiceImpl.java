package com.in.sms.service.serviceImpl;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.dto.student.StudentRequestDto;
import com.in.sms.dto.student.StudentResponseDto;
import com.in.sms.dto.student.StudentSearchDto;
import com.in.sms.model.Parents;
import com.in.sms.model.Semester;
import com.in.sms.model.Student;
import com.in.sms.repository.ParentsRepository;
import com.in.sms.repository.StudentRepository;
import com.in.sms.service.serviceInterfaces.SemesterService;
import com.in.sms.service.serviceInterfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private ParentsRepository parentsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto requestDto) {
        Student student=mapRequestToStudent(requestDto);
        student.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        String sem=student.getSemester().getName();
        SemesterResponseDto semester= semesterService.getSemesterByName(sem);
        Semester s=mapToSemester(semester);
        if(s!=null){
            student.setSemester(s);
        }else{
            Semester semester1 =new Semester(student.getSemester().getName());
            SemesterResponseDto semester2 = semesterService.saveSemester(semester1);
            student.setSemester(mapToSemester(semester2));
        }
        Optional<Parents> p=parentsRepository.findParentsByFatherNameAndMotherNameAndSiblings(requestDto.getParents().getFatherName(),requestDto.getParents().getMotherName(),requestDto.getParents().getSiblings());
        p.ifPresent(student::setParents);
        return mapStudentToResponse(studentRepository.save(student));
    }

    public Semester mapToSemester(SemesterResponseDto responseDto){
        Semester semester=new Semester();
        semester.setName(responseDto.getName());
        semester.setId(responseDto.getId());
        return semester;
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Optional<Student> optional=studentRepository.findById(id);
        if(optional.isPresent()){
            return mapStudentToResponse(optional.get());
        }else throw new RuntimeException("No student found for ID: " + id);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream().map(this::mapStudentToResponse).toList();
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        Student updatedStudent=mapRequestToStudent(requestDto);
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isPresent()) {
            updatedStudent.setId(id);
            if(requestDto.getPassword()!=null){
                updatedStudent.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            }else {
                updatedStudent.setPassword(existing.get().getPassword());
            }
            return mapStudentToResponse(studentRepository.save(updatedStudent));
        }
        throw new RuntimeException("Student not found for id: "+id);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentResponseDto> searchStudent(StudentSearchDto studentSearchDto, Pageable pageable) {
        Page<Student> studentPage=studentRepository.searchStudents(studentSearchDto,pageable);
        List<StudentResponseDto> dtoList=studentPage.getContent().stream().map(this::mapStudentToResponse).toList();
        return new PageImpl<>(dtoList,studentPage.getPageable(),studentPage.getTotalPages());
    }

    public Student mapRequestToStudent(StudentRequestDto studentDTO){
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setRollNo(studentDTO.getRollNo());
        student.setSemester(studentDTO.getSemester());
        student.setBranch(studentDTO.getBranch());
        student.setPhoneNo(studentDTO.getPhoneNo());
        student.setCategory(studentDTO.getCategory());
        student.setAddress(studentDTO.getAddress());
        student.setParents(studentDTO.getParents());
        student.setDocument(studentDTO.getDocument());
        student.setRoles(List.of("STUDENT"));
        return student;
    }

    public StudentResponseDto mapStudentToResponse(Student student){
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setRollNo(student.getRollNo());
        studentResponseDto.setPhoneNo(student.getPhoneNo());
        studentResponseDto.setSemester(student.getSemester());
        studentResponseDto.setBranch(student.getBranch());
        studentResponseDto.setCategory(student.getCategory());
        studentResponseDto.setAddress(student.getAddress());
        studentResponseDto.setParents(student.getParents());
        studentResponseDto.setDocument(student.getDocument());
        return studentResponseDto;
    }
}
