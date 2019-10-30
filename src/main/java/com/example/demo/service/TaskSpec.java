package com.example.demo.service;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
/**
* @author : ShengShuli
* @Date: 2019年10月30日
* @Description:
* 不同的查询条件都需要实现Specification重写toPrecidate
*/
public class TaskSpec {
	/**
	 * 混合查询
	 */
	@SuppressWarnings("serial")
	public static Specification<Task> methodOne(){
		return new Specification<Task>() {
			//不同的查询条件实现不同的断言
			@Override
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> exp1 = root.get("taskName");
				Path<Date> exp2 = root.get("createTime");
				Path<String> exp3 = root.get("taskDetail");
				Predicate predicate = cb.and(cb.like(exp1, "%任务%"),cb.lessThan(exp2,new Date()));
				return cb.or(predicate,cb.equal(exp3, "XXX"));
			}
		};
	}
	
	/**
	 * 多表关联查询
	 */
	@SuppressWarnings("serial")
	public static Specification<Task> methodTwo(){
		return new Specification<Task>() {
			
			@Override
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Task,Project> join = root.join("project", JoinType.INNER);
				Path<String> exp4 = join.get("projectName");
				return cb.like(exp4, "%项目%");
			}
		};
	}

}
