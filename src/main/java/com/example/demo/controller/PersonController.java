package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GenerationType;
import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Person;
import com.example.demo.entity.Phone;
import com.example.demo.repos.PersonRepository;
import com.example.demo.repos.PhoneRepository;
import com.github.wenhao.jpa.Specifications;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:Person的控制接口
*/
@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	
	@GetMapping("/add")
	public void addNewPerson() {
		Person person = new Person();
		person.setAge(21);
		person.setBirthday(new Date());
		person.setCompany("亦庄开发区");
		person.setName("shengshuli");
		person.setNickName("woodler");
		personRepository.save(person);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Person> getAllPersons(){
		List<Person> personList = new ArrayList<Person>();
		personList = personRepository.findAll();
		return personList;
	}
	
	/**
	 * 查询条件
	 * 昵称：dog
	 * 姓名：jack\Eric\null
	 * 公司：null
	 */
	@RequestMapping("/dog")
	@ResponseBody
	public List<Person> getPersonsByDog(ServletRequest request){
		//创建规格参数对象
		Specification<Person> specification = new Specifications()
				//昵称dog
				.eq("nickName","dog")
				.eq("name", "jack","Eric",null)
				.eq("company", null)
				.build();
		return personRepository.findAll(specification);
	}
	
	/**
	 * 多表查询
	 * 姓名：jack
	 * 品牌：HUAWEI
	 */
	@RequestMapping("/pp")
	@ResponseBody
	public List<Phone> getPhoneAndPersons(ServletRequest request){
		//创建规格参数对象
		 Specification<Phone> specification = new Specifications()
				 //手机品牌
				 .eq("brand", "HUAWEI")
				 //姓名
				 .eq("person.name", "jack")
				 .build();
		 return phoneRepository.findAll(specification);
	}
	
	/**
	 * 删除person端同时级联删除phone中的数据
	 */
	@RequestMapping("/del_person")
	@ResponseBody
	public String delPersonById(@RequestParam Long id) {
		personRepository.deleteById(id);
		return "del person ok";
	}
	
	//删除phone时不能级联删除person中的信息
	@RequestMapping("/del_phone")
	@ResponseBody
	public String delPhoneById(@RequestParam Long id) {
		phoneRepository.deleteById(id);
		return "del phone ok";
	}
	
	/**
	 * 综合使用ExampleMatcher
	 * 默认的匹配器：不忽略大小写，精确匹配
	 * 查出以"sheng"开头的，年龄21的person
	 */
	@RequestMapping("/em")
	@ResponseBody
	public List<Person> getPersonsByExample(){
		List<Person> plist = new ArrayList<Person>();
 		//创建实体对象-查询条件的数值
		Person person = new Person();
		person.setName("sheng");
		person.setAge(21);
		//创建实例匹配器-查询条件的方式
		ExampleMatcher matcher = ExampleMatcher.matching()
				//name开头查询
				.withMatcher("name", GenericPropertyMatchers.startsWith())
				//忽略大小写
				.withIgnoreCase(true)
				//可以包含空值
				.withIgnoreNullValues()
				//修改默认的字符串匹配方式-改为模糊查询
				.withStringMatcher(StringMatcher.CONTAINING);
		//创建实例对象（条件 + 方式）
		Example<Person> example = Example.of(person, matcher);
		plist = personRepository.findAll(example);
		return plist;
	}
	
	
	
	
	
	

}
