package com.in.sms.repository;

import com.in.sms.model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentsRepository extends JpaRepository<Parents,Long> {

    Optional<Parents> findParentsByFatherNameAndMotherNameAndSiblings(String fatherName, String motherName, Integer siblings);
}
