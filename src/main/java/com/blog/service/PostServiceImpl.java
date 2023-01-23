package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entites.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
 @Autowired
private PostRepository postRepository;
 @Autowired
private ModelMapper mapper;
@Override
public PostDto createPost(PostDto postDto) {
	Post post= mapToEntity(postDto);
	  Post posts = postRepository.save(post);
return mapToDto(posts);

}


private PostDto mapToDto(Post posts) {
	 PostDto postDto = mapper.map(posts, PostDto.class);
	
	
	return postDto;
}


private Post mapToEntity(PostDto postDto) {
	Post post = mapper.map(postDto, Post.class);
	
	return post;
}


@Override
public List<PostDto> getAllPosts() {
	List<Post> posts = postRepository.findAll();
	
	return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
}


@Override
public PostDto getPostId(long id) {
	   Post post = postRepository.findById(id).orElseThrow(
	()-> new ResourceNotFoundException("post", "id", id));
	
	
	return mapToDto(post);
}


@Override
public PostDto getUpdatePost(long id, PostDto postDto) {
	Post post = postRepository.findById(id).orElseThrow(()->
	new ResourceNotFoundException("post", "id", id)); 
	  post.setTitle(postDto.getTitle());
	  post.setDescription(postDto.getDescription());
	  post.setContent(postDto.getContent());
      Post save = postRepository.save(post);	
	
	return mapToDto(save);
}


@Override
public void deletePost(long id) {
  Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
	postRepository.delete(post);
	
}


	
}
