package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.StudentService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class TeacherController {

    @Autowired
    private StudentService studService;
    @Autowired
    private TeacherRepository tr;
    @Autowired
    private CourseRepository cr;
    @Autowired
    private StudentRepository sr;

    
    @Data
    @AllArgsConstructor
class TempStudent{
    
    int id;

    String name;
    String gender;
    String standard;


}


    @GetMapping(value="/teacher/{id}")
public Map<String,Object> getTeacherById(@PathVariable("id")int id) throws RecordNotFoundException {
   //teacher details and list of students and course details

    Optional<Teacher> opt = tr.findById(id);
    if(!opt.isPresent()){
        throw new RecordNotFoundException("Teacher not found in db.");
    }

    Map<String,Object> map = new HashMap<>();
   
    map.put("teacher-details", opt.get());

    map.put("Course-details",opt.get().getCourse());

    Course course= cr.findById(opt.get().getCourse().getId()).get();
    
    List<Student> lst = sr.findByCourse(course);
    List<TempStudent> lst2 = new ArrayList<>() ;
    lst.forEach(s-> lst2.add(new TempStudent(s.getId(),s.getUser().getUsername(),s.getGender(),s.getStandard())) );
    

map.put("Studentssss", lst2);




    return map;



}

@DeleteMapping("/admin/teacher/{id}")
public ResponseEntity<Map> deleteStudent(@PathVariable("id") int id) throws RecordNotFoundException{
   tr.deleteById(id);
   Map<String,String> map = new HashMap();
   map.put("Response", "Teacher deleted successfully..");



   return new ResponseEntity(map, HttpStatus.NO_CONTENT);


}
@PostMapping("/admin/teacher")
public ResponseEntity<Object>createTeacher(@Valid @RequestBody Teacher t){
     Teacher obj = tr.save(t);
     
    URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(loc).body("created in database");
    
}




@GetMapping("/admin/teacher")
public List<Teacher> showAllTeacher(){

    return tr.findAll();
}
}