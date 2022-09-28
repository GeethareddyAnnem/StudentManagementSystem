package com.example.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository sr;

    @Autowired
    CourseRepository cr;
    @Test
    void testFindByCourse() {
        Course c = new Course().builder().id(1).name("Mathematics").description("This is mathematics.").build();
        
        List<Course>lstOfCourses = new ArrayList<>();
        lstOfCourses.add(c);
        cr.save(c);

        User  user = User.builder()

                .username("studentTest")
              
                .password("Test@12345")
                .enabled(true)
               
                .build();
        Student s = new Student().builder().id(101).user(user).standard("Tenth").gender("Female").course(lstOfCourses).build();
        
       
            c.setStudent(s);
        sr.save(s);

        assertEquals(sr.findAll(),sr.findByCourse(cr.findById(1).get()));




                         
      

        



    }
}
