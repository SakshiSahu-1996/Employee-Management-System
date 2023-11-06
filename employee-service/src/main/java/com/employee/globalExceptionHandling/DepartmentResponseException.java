package com.employee.globalExceptionHandling;

public class DepartmentResponseException extends RuntimeException {
	public DepartmentResponseException(String message) {
		super(message);
	}
}
