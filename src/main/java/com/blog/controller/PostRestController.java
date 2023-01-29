package com.blog.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.PostDto;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
	@Autowired
	private PostService postService;
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
		return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.CREATED);	
		}
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PostDto>getAll(){
		
	 return postService.getAllPosts();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto>getPostId(@PathVariable("id")long id){
		return ResponseEntity.ok(postService.getPostId(id));

	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> getUpdate(@RequestBody PostDto postDto,@PathVariable("id")long id){
		PostDto dto = postService.getUpdatePost(id,postDto);
		
		return new ResponseEntity<>(dto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deletePost(@PathVariable("id")long id){
	postService.deletePost(id);
		return new ResponseEntity<>("SuccessFully Delete",HttpStatus.OK);
	}
	
}
