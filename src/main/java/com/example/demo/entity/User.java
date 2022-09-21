package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;


import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
@Component
public class User{
    @Id
    
	@NotBlank(message = "UserName should not be empty")
	@Length(min = 3,max=40,message = "length should be 3 to 40 chars only")
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = Access.WRITE_ONLY)
    //@Column(columnDefinition = "varchar(255) default 'ROLE_STUDENT'")
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Set<Role> roles;
   
    private boolean enabled;


 


}
