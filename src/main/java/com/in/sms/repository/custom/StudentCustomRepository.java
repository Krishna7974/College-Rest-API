package com.in.sms.repository.custom;

import com.in.sms.dto.StudentSearchDto;
import com.in.sms.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentCustomRepository {
    Page<Student> searchStudents(StudentSearchDto studentSearchDto, Pageable pageable);
}
