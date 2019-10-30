package com.example.demo.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.repos.ProjectDao;
import com.example.demo.repos.TaskDao;
import com.example.demo.service.TaskService;

/**
* @author : ShengShuli
* @Date: 2019年10月30日
* @Description:
*/
@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private TaskService taskService;
	
	/**
	 * 新增project和task
	 */
	@RequestMapping("/save_pt")
	@ResponseBody
	public String addNewProjectOrTask(@RequestParam Integer flag) {
		if(flag == 0) {
			Project project = new Project();
			project.setId(1l);
			project.setProjectName("项目1");
			projectDao.save(project);
		}else {
			Task task = new Task();
			task.setId(1l);
			task.setCreateTime(new Date());
			task.setTaskDetail("XXX");
			task.setTaskName("任务1");
			Project project = new Project();
			project.setId(1l);
			project.setProjectName("项目1");
			task.setProject(project);
			taskDao.save(task);
		}
		return "save ok";
	}
	
	//复杂查询
	@RequestMapping("/find")
	@ResponseBody
	public Page<Task> findBySpec(@RequestParam int page,@RequestParam int size){
		//return taskService.findBySpec(page, size);
		return taskService.methodThree(page, size);
	}
	

}
