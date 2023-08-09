package com.nal.teamc.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nal.teamc.dtos.AddPostDTO;
import com.nal.teamc.dtos.PostDTO;
import com.nal.teamc.enties.Post;

public interface IPostService {
	Page<PostDTO> getAllPosts(Pageable pageable);
	Page<PostDTO> getPostByTitle(String title, Pageable pageable);
	Post addPost (AddPostDTO addPostDTO, int userId);
	Integer addLike2Post(int postId);
	List<PostDTO> getLatestFivePosts();
	List<PostDTO> getMostLikedPosts();
	PostDTO getPostById(int id);
	List<PostDTO> getPostsByCategoryId(int categoryId);
}
