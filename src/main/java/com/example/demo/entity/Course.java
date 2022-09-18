package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@NoArgsConstructor



public class Course {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Name should not be empty")
	@Length(min = 3,max=40,message = "length should be 3 to 40 chars only")
	private String name;
	@NotBlank(message = "descrption is mandatory")
	private String description;
 
    
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;


    @OneToOne(cascade = CascadeType.MERGE,mappedBy = "course")
    @JsonManagedReference
    private Teacher teacher;


    public Course(Integer id,
            @NotBlank(message = "Name should not be empty") @Length(min = 3, max = 40, message = "length should be 3 to 40 chars only") String name,
            @NotBlank(message = "descrption is mandatory") String description,
            @NotBlank(message = "student is mandatory") @Valid Student student,@Valid Teacher teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.student = student;
        this.teacher = teacher;
    }
    

	


}
