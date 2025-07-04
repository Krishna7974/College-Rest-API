package com.in.sms.repository;

import com.in.sms.model.Teacher;
import com.in.sms.repository.custom.TeacherCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> , TeacherCustomRepository {
    Optional<Teacher> findByEmail(String email);
}
