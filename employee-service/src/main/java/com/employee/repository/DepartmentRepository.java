package com.employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.entity.document.Department;


public interface DepartmentRepository extends MongoRepository<Department, String> {

	Optional<Department> findByDepartmentId(String departmentId);
}
