package com.example.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

/**
* @author : ShengShuli
* @Date: 2019年10月28日
* @Description:
*/
public interface UserJpaRepository extends JpaRepository<User,Integer>{
	//注解式查询
	@Query("select u from User u where u.name = ?1")
	Page<User> findByName(String name,PageRequest pr);

}
