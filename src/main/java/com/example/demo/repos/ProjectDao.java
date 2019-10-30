package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Project;

/**
* @author : ShengShuli
* @Date: 2019年10月30日
* @Description:
*/
public interface ProjectDao extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project>{

}
