package com.in.sms.service;

import com.in.sms.model.Attendance;
import com.in.sms.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
//        return attendanceRepository.findByStudentId(studentId);
        return null;
    }


    @Override
    public List<Attendance> getAttendanceByDate(LocalDateTime date) {
//        return attendanceRepository.findByDate(date);
        return null;
    }

}
