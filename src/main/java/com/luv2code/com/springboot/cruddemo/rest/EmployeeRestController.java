package com.luv2code.com.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.com.springboot.cruddemo.entity.Employee;
import com.luv2code.com.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	//inject dao directly ... constructor injection
	@Autowired 
	public EmployeeRestController(EmployeeService  theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	//expose /employee to return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		
		return employeeService.findAll();
	}
	
	//reading a single employee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int  employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found = " + employeeId);
		}
		return theEmployee;
	}
	
	//add a new Employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		//just in case they pass an id , set id=0, to force a save operation
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		
		return theEmployee; 
	}
	
	//add mapping for PUT /employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//add mapping for DELETE /employee/{employeeId}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		
	   Employee	 tempEmployee = employeeService.findById(employeeId);
	   if(tempEmployee == null) {
		   throw new RuntimeException("Employee with that id not found. id- " + employeeId);
	   }
	    employeeService.deleteById(employeeId);
		return "Deleted employee with id - " + employeeId;
	}
	
	
	
	
	
}
