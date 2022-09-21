package com.example.demo.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@NoArgsConstructor



public class Teacher {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
	private User user;


	@NotBlank(message = "gender is mandatory")
	private String gender;
	


    @OneToOne(cascade = {CascadeType.ALL})
    @JsonBackReference
	private Course course;

    
    public Teacher(Integer id,
           @Valid User user,
            @NotBlank(message = "gender is mandatory") String gender,
           @Valid Course course) {
        this.id = id;
        this.user = user;
        this.gender = gender;
        this.course = course;
    }


	


}
