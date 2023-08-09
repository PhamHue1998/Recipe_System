/**
 * 
 */
package com.nal.teamc.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nal.teamc.dtos.CommentDTO;
import com.nal.teamc.enties.Comment;
import com.nal.teamc.services.CommentService;
import com.nal.teamc.services.ISessionService;

/**
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentSer;
	
	@Autowired
	ISessionService sessionService;
	
	/**
	 * Post Comment
	 * @param comment
	 * @return comment object
	 */
	@RequestMapping(value="/post", method = RequestMethod.POST)
	ResponseEntity<Comment> postComment(@RequestBody @Valid CommentDTO commentDTO, @RequestParam("sessionId") String sessionId) {
		
		// Check login session is valid and get user id from it
        int userid = sessionService.isSessionExpired(sessionId);
        
        // if login session is not valid, userid value will be 0, return 403 to client
        if(userid <= 0) {
            SecurityContextHolder.clearContext();
            System.out.println("ass");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
		
        // if login session is valid, save post comment
        commentDTO.setUserId(userid);
		Comment rs = commentSer.postComment(commentDTO);
		// if save fail, return 400 error to client 
		if(Objects.isNull(rs)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		// if save sucessfully, return 200 to client
		return ResponseEntity.ok(rs);
	}
	
	
	/**
	 * @param postId
	 * @return list comment by post id
	 */
	@RequestMapping(value="/{postId}", method = RequestMethod.GET)
	ResponseEntity<List<Comment>> getComments(@PathVariable("postId") int postId) {
		
		// Get all comment of post by post id
		List<Comment> rs = commentSer.getAllCommentByPostId(postId);
		// Sucessfully, return 200 to client
		if(!rs.isEmpty()) {
			return ResponseEntity.ok(rs);
		}
		// If has any error, return 400 to client
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
