package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {

	private StudentService studService;


	
	public StudentController(StudentService studService) {
		this.studService = studService;
	}


	@GetMapping("/hello")
	public String hello() {
		
	
		return "i'm student";
	}
	
	
	@GetMapping("/students")
	public List<Student>students(){
		return studService.fetchAllStudent();
		 
	}

	@PostMapping("/students")
	public Student createStudent(@RequestBody Student s){

		return  studService.createStudent(s);

	}

	
	

}
