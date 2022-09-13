package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.StudentService;

@RestController
public class TeacherController {

    @Autowired
    private StudentService studService;
    @Autowired
    private TeacherRepository tr;

    




    @GetMapping(value="/teacher/{id}")
public Teacher getTeacherById(@PathVariable("id")int id) throws RecordNotFoundException {
   //teacher details and list of students and course details

    Optional<Teacher> opt = tr.findById(id);
    if(!opt.isPresent()){
        throw new RecordNotFoundException("Teacher not found in db.");
    }

    return opt.get();


}


}
