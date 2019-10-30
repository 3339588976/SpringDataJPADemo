package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
* @author : ShengShuli
* @Date: 2019年10月28日
* @Description:关系维护端:绑定和解绑关系
*/
@Entity
@Table(name = "t_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String password;
	
	@ManyToMany
	private List<Authority> auth;
	//关联表(两个外键)
	@JoinTable(name = "t_user_auth",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "auth_id"))
	public List<Authority> getAuthList() {
		return auth;
	}
	public void setAuthList(List<Authority> authList) {
		this.auth = authList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
