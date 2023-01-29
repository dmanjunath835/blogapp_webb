package com.blog.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entites.Role;
import com.blog.entites.User;
import com.blog.payload.LoginDto;
import com.blog.payload.SignUpDto;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/signin")
	private ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
       	   Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authenticate);	
		return new ResponseEntity<>("User sigin_successfully",HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?>registorUser(@Valid @RequestBody SignUpDto signUpDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
		}
		if(userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email  already taken!",HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username already taken!",HttpStatus.BAD_REQUEST);
			
		}
		User user=new User();
		user.setName(signUpDto.getName());
		user.setEmail(signUpDto.getEmail());
		user.setUsername(signUpDto.getUsername());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		Role roles=roleRepository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));
		userRepository.save(user);	
		return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
	}
}
