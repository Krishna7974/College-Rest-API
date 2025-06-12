package com.in.sms.service;

import com.in.sms.model.Attendance;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceService {
    Attendance markAttendance(Attendance attendance);
    Attendance getAttendanceById(Long id);
    List<Attendance> getAllAttendance();
    List<Attendance> getAttendanceByStudentId(Long studentId);
    List<Attendance> getAttendanceByDate(LocalDateTime date);
}
