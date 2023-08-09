/**
 * 
 */
package com.nal.teamc.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 */
public class CommentDTO {
	
	@NotNull(message = "postId is required")
	private int postId;
	@Size(max = 225, message = "Content length exceeds the limit")
	private String content;
	private Timestamp createdAt;
	private int userId;
	
	/**
	 * @return
	 */
	public int getPostId() {
		return postId;
	}
	/**
	 * @param postId
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}
	/**
	 * @return
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt セットする createdAt
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
