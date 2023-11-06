package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.enums.Status;
import com.employee.globalExceptionHandling.EmployeeResponseException;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Long create(Employee employee) {
		Employee saved = employeeRepository.save(employee);
		return saved.getId();
	}

	public Employee findById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			Employee getOrder = employee.get();
			return getOrder;
		} else {
			return null;
		}
	}

	public Employee updateById(Long id, Employee employee) {
			Employee savedEmployee = findById(id);
			if (savedEmployee != null) {
				savedEmployee.setFirstName(employee.getFirstName());
				savedEmployee.setLastName(employee.getLastName());
				savedEmployee.setEmail(employee.getEmail());
				savedEmployee.setCreatedAt(employee.getCreatedAt());
				savedEmployee.setUpdatedAt(employee.getUpdatedAt());
				savedEmployee.setDepartmentId(employee.getDepartmentId());

				return employeeRepository.save(savedEmployee);
				//return Status.SUCCESS;
			}else
			{
				throw new EmployeeResponseException("EmployeeId not found for update: " + id);
			}
	}

	public Status deleteById(Long id) {
		employeeRepository.deleteById(id);
		return Status.SUCCESS;
	}
	
	public List<Employee> findByDepartmentId(String departmentId) {
	    List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
	    if (!employees.isEmpty()) {
	        return employees;
	    } else {
	        return null;
	    }
	}
}
