package com.example.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.User;

/**
* @author : ShengShuli
* @Date: 2019年10月28日
* @Description:分页排序查询接口
*/
public interface UserPagingAndSortintRepository extends PagingAndSortingRepository<User,Integer>{

	Page<User> findByName(String name,PageRequest pr) throws Exception;


}
