package com.junit_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junit_demo.entity.Attendance;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {

}
