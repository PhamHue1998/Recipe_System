/**
 * 
 */
package com.nal.teamc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nal.teamc.dtos.CommentDTO;
import com.nal.teamc.enties.Comment;
import com.nal.teamc.enties.Post;
import com.nal.teamc.enties.User;
import com.nal.teamc.reponsives.CommentResponsitory;
import com.nal.teamc.reponsives.PostResponsitory;
import com.nal.teamc.reponsives.UserRepository;

/**
 * 
 */
@Service
@Transactional
public class CommentService {

	@Autowired
	CommentResponsitory commentRes;

	@Autowired
	UserRepository userRes;

	@Autowired
	PostResponsitory postRes;

	/**
	 * Create post comment
	 * @param comment
	 * @return
	 */
	public Comment postComment(CommentDTO commentDTO) {

		Comment comment = new Comment();

		try {
			// Get user data and post data
			User user = userRes.findById(commentDTO.getUserId());
			Post post = postRes.findById(commentDTO.getPostId()).get();

			comment.setUser(user);
			comment.setPost(post);
			// Set comment data 
			comment.setContent(commentDTO.getContent());
		} catch (Exception ex) {
			System.out.print(ex.toString());
			return null;
		}

		// Save comment
		return commentRes.save(comment);
	}

	/**
	 * Get all comments of post
	 * @param postId
	 * @return
	 */
	public List<Comment> getAllCommentByPostId(int postId) {
		return commentRes.findAllByJPost(postId);
	}

}
