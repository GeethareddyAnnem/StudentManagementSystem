package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.controller.RecordNotFoundException;
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
      
       return sr.save(s);
    }






    @Override
    public List<Student> getallList() {
        
        return sr.findAll();
    }






    @Override
    public Student getStudentbyId(int id) throws RecordNotFoundException {
        Optional<Student> sOpt= sr.findById(id);
        

       
        if(!sOpt.isPresent()){
           throw new RecordNotFoundException("Student not found db.");
        }
        else{
            return sOpt.get();
        }

        
        
    }






    @Override
    public void deleteStudent(int id) throws RecordNotFoundException {

        Optional<Student> sOpt= sr.findById(id);
        

       
        if(!sOpt.isPresent()){
           throw new RecordNotFoundException("given student id not found in db");
        }
        else{
            sr.deleteById(id);
        }
       
        
    }






    @Override
    public Student updateStudent(int id, Student s) {
       
       
    Optional<Student> op = sr.findById(id);
    if (!op.isPresent()) {
        throw new RecordNotFoundException("student is not found in db");

    } else if (op.get().getId() == id) {
     
        if (s.getGender() != null && !("".equals(s.getGender()))) {
            op.get().setGender(s.getGender());
        }

        if (s.getStandard() != null && !("".equals(s.getStandard()))) {
            op.get().setStandard(s.getStandard());
        }

        sr.save(op.get());
        return op.get();

    }
   

    return null;

    }

}
