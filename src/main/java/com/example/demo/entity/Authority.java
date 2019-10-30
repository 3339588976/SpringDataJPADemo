package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:用户-权限：多对多
* 关系被维护端
*/
@Entity
@Table(name = "t_auth")
public class Authority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	//User中Authority对象
	@ManyToMany(mappedBy = "auth")
	private List<User> user;
	public List<User> getUserList() {
		return user;
	}
	public void setUserList(List<User> userList) {
		this.user = userList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
