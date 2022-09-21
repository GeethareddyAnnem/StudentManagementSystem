package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {

	private StudentService studService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;


	
	public StudentController(StudentService studService) {
		this.studService = studService;
	}
	@PostMapping("/admin/student")
	public ResponseEntity<Object>createStudent(@Valid @RequestBody Student s){
		s.getUser().setPassword(passwordEncoder.encode(s.getUser().getPassword()));
	     Student studentobj = studService.createStudent(s);
	     
	    URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentobj.getId()).toUri();
	    return ResponseEntity.created(loc).body("created in database");
	    
	}


	@GetMapping("/admin/student")
	public List<Student> getAllStudent(){
		return studService.getallList();
	}
	
	
		
	
		
	
@GetMapping(value="/student/{id}")
public Student getStudentById(@PathVariable("id")int id) throws RecordNotFoundException {
    return studService.getStudentbyId(id);
}
@DeleteMapping("/admin/student/{id}")
public ResponseEntity<Map> deleteStudent(@PathVariable("id") int id) throws RecordNotFoundException{
   studService.deleteStudent(id);
   Map<String,String> map = new HashMap();
   map.put("Response", "Student deleted successfully..");
   return new ResponseEntity(map, HttpStatus.NO_CONTENT);


}

@PutMapping("/admin/student/{id}")
public Student updateStudent(@PathVariable("id") int id, @RequestBody Student s){
    
	return studService.updateStudent(id,s);
}

}
