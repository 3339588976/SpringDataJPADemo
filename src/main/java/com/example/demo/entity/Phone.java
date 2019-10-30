package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:手机号实体类，一个人有多个手机号
* 多端不能改变One端
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "t_phone")
public class Phone implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String number;
	private String brand;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	@JsonBackReference
	public Person getPerson() {
		return person;
	}
	@JsonBackReference
	public void setPerson(Person person) {
		this.person = person;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
