package com.in.sms.repository;

import com.in.sms.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester,Long> {

//    List<Student> findStudentsByClassRoomId(Long id);
//
//    @Query("SELECT t FROM Teacher t JOIN t.classRooms c WHERE c.id = :classRoomId")
//    List<Teacher> findTeachersByClassRoomId(@Param("classRoomId") Long classRoomId);
//
//
//    List<Subject> findSubjectsByClassRoomId(Long id);
     Optional<Semester> getSemesterByName(String semesterName);

}
