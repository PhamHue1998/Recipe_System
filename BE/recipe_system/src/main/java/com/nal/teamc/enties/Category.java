package com.nal.teamc.enties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "`tb_categories`")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "category_name", length = 225, nullable = false)
	private String categoryName;

	@Column(name = "category_img", length = 225, nullable = false)
	private String categoryImg;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "modify_at")
	private Date modifyAt;

	@Column(name = "is_delete")
	private boolean isDelete;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category")
	private List<PostCategory> postCategories = new ArrayList<>();

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryImg() {
		return categoryImg;
	}

	public void setCategoryImg(String categoryImg) {
		this.categoryImg = categoryImg;
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

	public List<PostCategory> getPostCategories() {
		return postCategories;
	}

	public void setPostCategories(List<PostCategory> postCategories) {
		this.postCategories = postCategories;
	}


}
