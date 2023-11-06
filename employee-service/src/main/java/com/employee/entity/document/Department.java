package com.employee.entity.document;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "department")
public class Department {
	
	@Id
	private String departmentId;
	private String departmentName;
    @CreatedDate
	private LocalDate createdAt;
    @LastModifiedDate
	private LocalDate updatedAt;
}
