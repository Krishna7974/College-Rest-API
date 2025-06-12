package com.in.sms.service;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.dto.StudentSearchDto;
import com.in.sms.model.Semester;
import com.in.sms.model.Student;
import com.in.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SemesterService semesterService;

    @Override
    public Student saveStudent(Student student) {
        String sem=student.getSemester().getName();
        SemesterResponseDto semester= semesterService.getSemesterByName(sem);
        Semester s=mapToSemester(semester);
        if(s!=null){
            System.out.println();
            student.setSemester(s);
        }else{
            Semester semester1 =new Semester(student.getSemester().getName());
            SemesterResponseDto semester2 = semesterService.saveSemester(semester1);
            student.setSemester(mapToSemester(semester2));
        }
        System.out.println(s);
        return studentRepository.save(student);
    }

    public Semester mapToSemester(SemesterResponseDto responseDto){
        Semester semester=new Semester();
        semester.setName(responseDto.getName());
        semester.setId(responseDto.getId());
        return semester;
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> optional=studentRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else throw new RuntimeException("No student found for ID: " + id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isPresent()) {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByClassRoomId(Long classRoomId) {
        return null;
    }

    @Override
    public Page<Student> searchStudent(StudentSearchDto studentSearchDto, Pageable pageable) {
        return studentRepository.searchStudents(studentSearchDto,pageable);
    }

//    @Override
//    public Page<Student> searchStudent(StudentSearchDto studentSearchDto, Pageable pageable) {
//
//        if (studentSearchDto.getStdName() != null && !studentSearchDto.getStdName().isBlank()) {
//            return setPages(studentRepository.searchStudentsByName("%" + studentSearchDto.getStdName() + "%"),pageable);
//        }
//
//        if (studentSearchDto.getSem() != null && !studentSearchDto.getSem().isBlank()) {
//
//            return setPages(studentRepository.getStudentsBySemesterOrdered(studentSearchDto.getSem()),pageable);
//        }
//
//        if (studentSearchDto.getBranch() != null && !studentSearchDto.getBranch().isBlank()) {
//            return setPages(studentRepository.findByBranch(studentSearchDto.getBranch()),pageable);
//        }
//
//        if (studentSearchDto.getCategory() != null && !studentSearchDto.getCategory().isBlank()) {
//            return setPages(studentRepository.findByCategory(studentSearchDto.getCategory()),pageable);
//        }
//
//        if (studentSearchDto.getAddress() != null && !studentSearchDto.getAddress().isBlank()) {
//            return setPages(studentRepository.findByAddressContainingIgnoreCase(studentSearchDto.getAddress()),pageable);
//        }
//
//        if (studentSearchDto.getRollNo() != null && !studentSearchDto.getRollNo().isBlank()) {
//            return setPages(studentRepository.findByRollNo(studentSearchDto.getRollNo()),pageable);
//        }
//
//        if (studentSearchDto.getEmail() != null && !studentSearchDto.getEmail().isBlank()) {
//            return setPages(studentRepository.findByEmail(studentSearchDto.getEmail()),pageable);
//        }
//
//        return setPages(null,pageable);
//    }

//    public Page<Student> setPages(List<Student> studentList,Pageable pageable){
//        int startIndex=(int)pageable.getOffset();
//        if(studentList==null || studentList.isEmpty() || startIndex>studentList.size()) throw new RuntimeException("No Data Found");
//        int endIndex=Math.min(startIndex+pageable.getPageSize(),studentList.size());
//
//        List<Student> paginatedList=studentList.subList(startIndex,endIndex);
//        return new PageImpl<>(paginatedList,pageable,studentList.size());
//    }

}
