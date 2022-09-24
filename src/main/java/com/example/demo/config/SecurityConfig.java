package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

	


	

	@Bean
	 AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder,UserDetailsService jwtUserDetailsService) throws Exception {
	
		return builder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder()).and().build();
		
	}

	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	
	
	
	
	
	
	
	
	
	
	@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,JwtRequestFilter jwtRequestFilter) throws Exception {
		// We don't need CSRF for this example
		
		 final String[] AUTH_WHITELIST = {
		        "/swagger-resources/**",
		        "/swagger-ui/**",
		        "/v3/api-docs",
		        "/authenticate",
		        "/webjars/**"
		};
		httpSecurity.csrf().disable()
				.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
			
           
              
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/student/**").hasAnyAuthority("STUDENT","ADMIN")
                .antMatchers("/teacher/**").hasAnyAuthority("TEACHER","ADMIN")










			
				.anyRequest().authenticated().and()
			
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}