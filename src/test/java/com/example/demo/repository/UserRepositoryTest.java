package com.example.demo.repository;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest

public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    void testFindByUsername() {
        Set<Role> hs = new HashSet<>();
        hs.add(new Role(1, "ADMIN"));
        User user = User.builder()

                .username("test")
              
                .password("Test@123")
                .enabled(true)
                .roles(hs)
                .build();
                
                
        
        
        
       
        userRepository.save(user);
        assertThat(Optional.of(user)).isEqualTo(userRepository.findByUsername(user.getUsername()));
        
    }
}
