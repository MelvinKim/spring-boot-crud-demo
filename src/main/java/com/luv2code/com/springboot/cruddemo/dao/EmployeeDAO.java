package com.luv2code.com.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.com.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteByid(int theId);
}
