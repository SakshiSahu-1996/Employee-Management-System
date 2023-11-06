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

import com.employee.entity.Employee;
import com.employee.enums.Status;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public Long createOrder(@RequestBody  Employee employee) {
		return employeeService.create(employee);
	}

	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		return employeeService.findById(id);
	}
	
	
	@PutMapping("/{id}")
	public Employee updateById(@PathVariable Long id,@RequestBody Employee employee) {
		return employeeService.updateById(id, employee);
	}

	@DeleteMapping("/{id}")
	public Status deleteById(@PathVariable Long id) {
		return employeeService.deleteById(id);
	}
}
