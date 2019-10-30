package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Phone;

/**
* @author : ShengShuli
* @Date: 2019年10月29日
* @Description:手机号Repository层接口
*/
public interface PhoneRepository extends JpaRepository<Phone,Long>,JpaSpecificationExecutor<Phone>{

}
