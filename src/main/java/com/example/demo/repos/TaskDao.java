package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Task;

/**
* @author : ShengShuli
* @Date: 2019年10月30日
* @Description:任务复杂查询的接口，继承JpaSpecificationExecuto
*/
public interface TaskDao extends JpaRepository<Task,Long>,JpaSpecificationExecutor<Task>{

}
