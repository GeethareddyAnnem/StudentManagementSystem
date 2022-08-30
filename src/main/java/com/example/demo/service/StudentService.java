package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;



@Service
public interface StudentService {

	 public List<Student> fetchAllStudent();

	public Student createStudent(Student s);
		
	
	
	
	

}
