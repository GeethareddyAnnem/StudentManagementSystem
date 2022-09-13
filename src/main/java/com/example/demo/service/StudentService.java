package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.controller.RecordNotFoundException;
import com.example.demo.entity.Student;



@Service
public interface StudentService {

	 public List<Student> fetchAllStudent();

	public Student createStudent(Student s);

	public List<Student> getallList();

    public Student getStudentbyId(int id) throws RecordNotFoundException;

    public void deleteStudent(int id) throws RecordNotFoundException;

    public Student updateStudent(int id, Student s);
		
	
	
	
	

}
