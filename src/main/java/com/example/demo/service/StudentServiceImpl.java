package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository sr;
    public StudentServiceImpl(StudentRepository sr) {
        this.sr = sr;
    }


    



    @Override
    public List<Student> fetchAllStudent() {
        

        return sr.findAll();
        
    }






    @Override
    public Student createStudent(Student s) {
        // TODO Auto-generated method stub
       return sr.save(s);
    }

}
