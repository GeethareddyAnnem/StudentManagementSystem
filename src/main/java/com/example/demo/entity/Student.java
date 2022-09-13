package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@NoArgsConstructor



public class Student {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
   @NotBlank(message = "Name should not be empty")
	@Length(min = 3,max=40,message = "length should be 3 to 40 chars only")
	private String name;
	@NotBlank(message = "gender is mandatory")
	private String gender;
	@NotBlank(message = "standard is mandatory")
	private String standard;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
 @JsonManagedReference
	private List<Course> course;

public Student(Integer id,
		@NotBlank(message = "Name should not be empty") @Length(min = 3, max = 40, message = "length should be 3 to 40 chars only") String name,
		@NotBlank(message = "gender is mandatory") String gender,
		@NotBlank(message = "standard is mandatory") String standard, @Valid List<Course> course) {
	this.id = id;
	this.name = name;
	this.gender = gender;
	this.standard = standard;
	this.course = course;
}
	
	


}
