package com.nal.teamc.enties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`tb_save_posts`")
public class SavePost implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	private Post post;
	
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "modify_at")
	private Date modifyAt;

	@Column(name = "is_delete")
	private boolean isDelete;
	
	public SavePost() {
		// TODO Auto-generated constructor stub
	}
}
