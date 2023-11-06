package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.document.Department;
import com.employee.enums.Status;
import com.employee.globalExceptionHandling.DepartmentResponseException;
import com.employee.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
		
	public String create(Department department) {
		Department saved = departmentRepository.save(department);
		return saved.getDepartmentId();
	}

	public Department findByDepartmentId(String departmentId) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		if (department.isPresent()) {
			Department getDepartment = department.get();
			return getDepartment;
		} else {
			return null;
		 		}
	}

	public Department updateByDepartmentId(String departmentId, Department department) {
		Department savedDepartment = findByDepartmentId(departmentId);
			if (savedDepartment != null) {
				savedDepartment.setDepartmentName(department.getDepartmentName());
				savedDepartment.setUpdatedAt(department.getUpdatedAt());
				savedDepartment.setCreatedAt(department.getCreatedAt());
				return departmentRepository.save(savedDepartment);
			}else
			{
			throw new DepartmentResponseException("EmployeeId not found for update: " + departmentId);
			}
	}

	public Status deleteByDepartmentId(String departmentId) {
		departmentRepository.deleteById(departmentId);
		return Status.SUCCESS;
	}
}
