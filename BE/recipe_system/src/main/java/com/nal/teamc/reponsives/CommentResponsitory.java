package com.nal.teamc.reponsives;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nal.teamc.enties.Comment;

public interface CommentResponsitory extends JpaRepository<Comment, Integer> {

	// Get all comments of post by post id
	@Query("from Comment c where c.post.id = :postid")
	List<Comment> findAllByJPost(int postid);
}
