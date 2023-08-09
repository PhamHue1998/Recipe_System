package com.nal.teamc.enties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "`tb_posts`")
public class Post  implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "post")
	private List<PostCategory> postCategories = new ArrayList<>();
	
	@Column(name = "title",length = 225 ,nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "like_number")
	private int likeNumber;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "modify_at")
	private Date modifyAt;
	
	@Column(name = "is_delete")
	private boolean isDelete;
	
	@JsonManagedReference
	@OneToMany(mappedBy="post")
	private List<Image> images = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy="post")
    private List<Comment> comments = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy="post")
    private List<Share> shares = new ArrayList<>();
	
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public List<PostCategory> getPostCategories() {
		return postCategories;
	}

	public void setPostCategories(List<PostCategory> postCategories) {
		this.postCategories = postCategories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifyAt() {
		return modifyAt;
	}

	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
