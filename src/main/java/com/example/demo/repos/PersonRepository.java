package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Person;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:Person的查询接口
*/
public interface PersonRepository extends JpaRepository<Person,Long>,JpaSpecificationExecutor<Person>{

}
