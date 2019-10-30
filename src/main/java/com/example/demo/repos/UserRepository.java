package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;

/**
* @author : ShengShuli
* @Date: 2019年10月28日
* @Description:Repository层的接口类，封装了JPA数据库底层的操作
* 其中泛型的参数代表：操作对象类型，对象主键的数据类型
* 操作的实体和ID(管理域类和域类ID作为参数)
*/
public interface UserRepository extends CrudRepository<User,Integer>{}
