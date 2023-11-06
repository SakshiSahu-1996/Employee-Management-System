package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.document.Department;
import com.employee.enums.Status;
import com.employee.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	    @Autowired
		private DepartmentService departmentService;
		
		@PostMapping
		public String createDepartment(@RequestBody Department department) {
			return departmentService.create(department);
		}

		@GetMapping("/{id}")
		public Department findByDepartmentId(@PathVariable String departmentId) {
			return departmentService.findByDepartmentId(departmentId);
		}
		
		@PutMapping("/{id}")
		public Department updateByDepartmentId(@PathVariable String id,@RequestBody Department department) {
			return departmentService.updateByDepartmentId(id, department);
		}

		@DeleteMapping("/{id}")
		public Status deleteByDepartmentId(@PathVariable String departmentId) {
			return departmentService.deleteByDepartmentId(departmentId);
		}
	}



