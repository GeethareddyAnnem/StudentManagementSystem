package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;




@Repository
public interface StudentRepository  extends JpaRepository <Student,Integer>{
	
public List<Student> findByCourse(Course course);
	

}
