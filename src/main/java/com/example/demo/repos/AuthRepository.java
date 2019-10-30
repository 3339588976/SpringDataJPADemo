package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Authority;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:权限接口
*/
public interface AuthRepository extends JpaRepository<Authority,Long>,JpaSpecificationExecutor<Authority>{

}
