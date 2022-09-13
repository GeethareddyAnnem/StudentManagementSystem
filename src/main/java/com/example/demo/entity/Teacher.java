package com.example.demo.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@NoArgsConstructor



public class Teacher {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Name should not be empty")
	@Length(min = 3,max=40,message = "length should be 3 to 40 chars only")
	private String name;
	@NotBlank(message = "gender is mandatory")
	private String gender;
	


    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
	private Course course;
    public Teacher(Integer id,
            @NotBlank(message = "Name should not be empty") @Length(min = 3, max = 40, message = "length should be 3 to 40 chars only") String name,
            @NotBlank(message = "gender is mandatory") String gender,
           @Valid Course course) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.course = course;
    }


	


}
