package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.repos.AuthRepository;
import com.example.demo.repos.UserJpaRepository;
import com.example.demo.repos.UserPagingAndSortintRepository;
import com.example.demo.repos.UserRepository;

/**
* @author : ShengShuli
* @Date: 2019年10月28日
* @Description:
*/
@Controller
@RequestMapping("/demo")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private UserPagingAndSortintRepository userPagingAndSortintRepository;
	@Autowired
	private AuthRepository authRepository;
	
	@GetMapping("/add")
	public void addNewUser(@RequestParam String name,@RequestParam String password) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		userRepository.save(user);
	}
	@RequestMapping("/list")
	@ResponseBody
	public List<User> getUserList(){
		List<User> userList = new ArrayList<User>();
		userList = (List<User>) userRepository.findAll();
		return userList;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Page<User> getUserByName(@RequestParam String name,@RequestParam Integer page,@RequestParam Integer size){
		//page从0开始
		if(page == null) {
			page = 1;
		}
		if(size == null || size == 0) {
			size = 10;
		}
		PageRequest pr = PageRequest.of(page, size);
		//Page<User> userPage = userJpaRepository.findByName(name, pr);//对象
		Page<User> userPage = userJpaRepository.findAll(pr);//对象列表
		return userPage;
	}
	
	@RequestMapping("/sort")
	@ResponseBody
	public Iterable<User> getAllUserWithSort(){
		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		return userPagingAndSortintRepository.findAll(sort);
	}
	
	@RequestMapping("/ps")
	@ResponseBody
	public Page<User> getAllUserByPage(){
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		PageRequest pr = PageRequest.of(1, 10, sort);
		return userPagingAndSortintRepository.findAll(pr);
	}
	
	//添加Auth信息
	@RequestMapping("/save_auth")
	@ResponseBody
	public String addNewAuth() {
		Authority auth = new Authority();
		auth.setId(1L);
		auth.setName("ROLE_ADMIN");
		authRepository.save(auth);
		return "save ok";
	}
	
	//添加User信息
	@RequestMapping("/save_user")
	@ResponseBody
	public String addNewUser() {
		User user = new User();
		user.setName("test");
		user.setPassword("666666");
		List<Authority> authList = new ArrayList<Authority>();
		Authority auth = authRepository.findById(1L).get();
		authList.add(auth);
		user.setAuthList(authList);
		userJpaRepository.save(user);
		return "save user ok";
	}
	
	//删除用户：关系维护端可以级联删除关联关系表中数据，关系被维护端不行
	@RequestMapping("/del_user")
	@ResponseBody
	public String delUserById(@RequestParam Integer id) {
		userJpaRepository.deleteById(id);
		return "del user ok";
	}
	
	
}
