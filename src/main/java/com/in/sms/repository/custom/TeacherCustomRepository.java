package com.in.sms.repository.custom;

import com.in.sms.dto.teacher.TeacherSearchDto;
import com.in.sms.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherCustomRepository {
    Page<Teacher> searchTeacher(TeacherSearchDto dto, Pageable pageable);
}
