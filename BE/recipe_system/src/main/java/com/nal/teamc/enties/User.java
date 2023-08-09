package com.nal.teamc.enties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "`tb_users`")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;

	@Column(name = "username", length = 20, nullable = true)
	private String username;
	
	@Column(name = "password", length = 225, nullable = false)
	private String password; 
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	
	@Column(name = "phone_number", length = 11)
	private String phoneNumber;
	
	@Column(name = "address", length = 225)
	private String address;
	
	@Column(name = "birthday")
	private Date birthday;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = true)
	private Sex sex; 
	
	@Column(name = "lnk_avatar", length = 225, nullable = true)
	private String lnk_avatar;
	
	@Column(name = "verification_code", length = 50, nullable = true)
	private String verificationCode;
	
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
    @Column(name = "modify_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifyAt;
	
	@Column(name = "is_delete")
	private boolean isDelete;
	
	@JsonManagedReference
	@OneToMany(mappedBy="user")
    private List<Post> posts = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy="user")
    private List<Comment> comments = new ArrayList<>();;
	
    @JsonManagedReference
	@OneToMany(mappedBy="user")
    private List<Share> shares = new ArrayList<>();;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Session> sessions;
	
	public enum Role {
		ADMIN, USER
	}
	
	public enum Sex{
		FEMALE, MALE, OTHER
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getLnk_avatar() {
		return lnk_avatar;
	}
	public void setLnk_avatar(String lnk_avatar) {
		this.lnk_avatar = lnk_avatar;
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
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
