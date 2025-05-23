package com.example.learningspringsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningspringsecurity.resources.model.entity.Employee;
import com.example.learningspringsecurity.resources.repository.EmpolyeeRep;


@Service
public class EmployeeService {

	@Autowired
	private EmpolyeeRep empRepo;
	
	public List<Employee> getUsers() {
		List<Employee> emps = empRepo.findAll();
		return emps;
	}

	public Employee getUser(Integer id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = empRepo.findById(id);
		return emp.orElse(null);
	}

	public Employee save(Employee emp) {
		Employee newEmp = empRepo.save(emp);
		return newEmp;
	}
	
	public Employee update(Employee emp) {
		Employee newEmp = empRepo.save(emp);
		return newEmp;
	}


	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 empRepo.deleteById(id);
	}
	
//	public void delete(Integer id) {
//		return empRepo.deleteById(id);
//
//	}

	
}
