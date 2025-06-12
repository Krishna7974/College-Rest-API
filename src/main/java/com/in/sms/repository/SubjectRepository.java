package com.in.sms.repository;

import com.in.sms.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    @Query("select s from Subject s order by s.code")
    List<Subject> findAllSubjectOrderByCode();

    Optional<Subject> findByName(String name);
}
