package com.luv2code.com.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.com.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		//create a query
		Query theQuery = entityManager.createQuery("from Employee");
				
	    //execute query and get  results list
		List<Employee> employees = theQuery.getResultList();
				
		//return the results		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		//get the employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		//return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		//return updated object
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteByid(int theId) {
		
		// find employee
		
		//delete employee
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}
	

}
