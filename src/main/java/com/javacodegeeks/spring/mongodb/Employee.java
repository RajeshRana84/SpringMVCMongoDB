package com.javacodegeeks.spring.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
public class Employee {
	@Id
	private String id;
	private String name;
	private Integer age;
	@DBRef
	private Company company;

	public Employee(){}
	
	public Employee(String id, String name, Company company) {
		this.id = id;
		this.name = name;
		this.company = company;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String toString() {
		return "Employee [" + getId() + ", " + getName() + ", " + getCompany() + "]";
	}
}
