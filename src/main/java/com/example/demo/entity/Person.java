package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:操作实体：人类
* One端可以改变多端，即删除Person，则对于的Phone会被删除
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "t_person")
public class Person implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;
	@Column(name = "nick_name")
	private String nickName;
	private String company;
	private Date birthday;
	/**
	 * One端是关系的维护端，One端可以改变多端
	 * mappedBy:person是多端Phone中的一端Person的属性
	 * cascade=all:级联保存，更新，删除，刷新
	 */
	@OneToMany(mappedBy = "person",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Phone> phones = new HashSet<Phone>();
	
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
