package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PostRestontroller {
	@Autowired
	private PostService postService;
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		PostDto dto = postService.createPost(postDto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
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
