package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@SpringBootTest
class SpringDataJpaDemoApplicationTests {
	@Autowired
	private TaskService service;

	@Test
	void contextLoads() {
		Page<Task> task = service.findBySpec(0, 10);
		task.forEach(t -> {
			System.out.println(t.getId() + t.getTaskName() + t.getTaskDetail());
		});
	}

}
