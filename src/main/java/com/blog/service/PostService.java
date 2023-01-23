package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);

	List<PostDto> getAllPosts();

	PostDto getPostId(long id);

	PostDto getUpdatePost(long id, PostDto postDto);

	void deletePost(long id);

}
