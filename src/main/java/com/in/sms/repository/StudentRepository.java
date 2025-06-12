package com.in.sms.repository;

import com.in.sms.model.Student;
import com.in.sms.repository.custom.StudentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , StudentCustomRepository {

    List<Student> getStudentByName(String name);

    List<Student> getStudentBySemesterId(Long id);

//    @Query("SELECT s FROM Student s WHERE s.semester.id = :id ORDER BY s.rollNo")
//    List<Student> getStudentsByClassRoomOrdered(@Param("id") Long id);

    @Query("select s from Student s where lower(s.name) like :name order by s.rollNo")
    List<Student> searchStudentsByName(@Param("name") String name);

    @Query("select s from Student s where s.branch =:branch order by s.rollNo")
    List<Student> findByBranch(@Param("branch") String branch);

    @Query("SELECT s FROM Student s WHERE s.semester.id = :semesterId ORDER BY s.rollNo")
    List<Student> getStudentsBySemesterOrdered(@Param("semesterId") String semesterId);

    List<Student> findByCategory(String category);

    List<Student> findByAddressContainingIgnoreCase(String address);

    List<Student> findByRollNo(String rollNo);

    List<Student> findByEmail(String email);
}
