package com.example.demo.service;

import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.repos.TaskDao;

/**
* @author : ShengShuli
* @Date: 2019年10月30日
* @Description:复杂查询的业务
*/
@Service
public class TaskService {
	@Autowired
	private TaskDao taskDao;
	
	/**
	 * 分页排序的复杂查询
	 * 内部类实现Specification
	 */
	public Page<Task> findBySpec(int page,int size) {
		PageRequest pr = this.buildPageRequest(page, size);
		//Page<Task> task = taskDao.findAll(new MySpec(), pr);
		Page<Task> task1 = taskDao.findAll(TaskSpec.methodOne(), pr);
		//Page<Task> task2 = taskDao.findAll(TaskSpec.methodTwo(), pr);
		
		return task1;
	}
	
	/**
	 * 建立分页排序请求:创建时间倒序
	 * @param page
	 * @param size
	 * @return
	 */
	private PageRequest buildPageRequest(int page,int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		return PageRequest.of(page, size, sort);
	}
	
	/**
	 * 自定义查询条件Specification,实现断言方法
	 * 内部类：Specification底层原理
	 */
	private class MySpec implements Specification<Task>{
		@Override
		public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			/**
			 * 1.混合条件查询 
			 * SELECT task0_.id AS id1_4_, task0_.create_time AS create_t2_4_,
			 * task0_.project_id AS project_5_4_, task0_.task_detail AS task_det3_4_,
			 * task0_.task_name AS task_nam4_4_ FROM t_task task0_ WHERE ( task0_.task_name
			 * LIKE '%任务%' ) AND task0_.create_time < NOW( ) AND task0_.task_detail = 'XXX'
			 * ORDER BY task0_.create_time DESC LIMIT 0, 10
			 */
//			Path<String> exp1 = root.get("taskName");
//			Path<Date> exp2 = root.get("createTime");
//			Path<String> exp3 = root.get("taskDetail");
//			Predicate predicate = cb.and(cb.like(exp1, "%任务%"),cb.lessThan(exp2,new Date()));
//			return cb.or(predicate,cb.equal(exp3, "XXX"));
			
			/**
			 * 2.多表查询
			 * 根对象task内联project
			 * Task类中的project属性
			 */
			
			Join<Task,Project> join = root.join("project", JoinType.INNER);
			Path<String> exp4 = join.get("projectName");
			return cb.like(exp4, "%项目%");
			
		}
	}
	/**
	 * 可以定义多个Specification,通过Specifications将对象连接
	 */
	@SuppressWarnings("serial")
	public Page<Task> methodThree(int page,int size) {
		PageRequest pr = this.buildPageRequest(page, size);
		Specification<Task> s1 = new Specification<Task>() {
			@Override
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> exp1 = root.get("taskName");
				Path<Date> exp2 = root.get("createTime");
				Path<String> exp3 = root.get("taskDetail");
				Predicate predicate = cb.and(cb.like(exp1, "%任务%"),cb.lessThan(exp2,new Date()));
				return cb.or(predicate,cb.equal(exp3, "XXX"));
			}
		};
		Specification<Task> s2 = new Specification<Task>() {
			@Override
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Task,Project> join = root.join("project", JoinType.INNER);
				Path<String> exp4 = join.get("projectName");
				return cb.like(exp4, "%项目%");
			}
		};
		Page<Task> taskList = taskDao.findAll(Specification.where(s1).and(s2), pr);
		return taskList;
	}
	
}
