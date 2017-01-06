package com.ifi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{
	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	
    private String password;
    
    private boolean actived;
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<UserRoles> roles = new ArrayList<>();
   
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="profilID")
    private Profile profil;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public List<UserRoles> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}
	public Profile getProfil() {
		return profil;
	}
	public void setProfil(Profile profil) {
		this.profil = profil;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, boolean actived) {
		super();
		this.username = username;
		this.password = password;
		this.actived = actived;
	}

}
