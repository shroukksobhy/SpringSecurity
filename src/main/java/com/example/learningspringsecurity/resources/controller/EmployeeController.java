package com.example.learningspringsecurity.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningspringsecurity.resources.model.entity.Employee;
import com.example.learningspringsecurity.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/get-emp")
	public ResponseEntity<Employee> getUser(@RequestParam Integer id) {
		Employee emp = empService.getUser(id);
		if(emp ==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empService.getUser(id));
	}
	
	@GetMapping("/getall")
	public List<Employee> getUsers() {
		return empService.getUsers();
	}
	
	@PostMapping("/save")
	public Employee saveEmp(@RequestBody Employee emp ) {
		return empService.save(emp);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmp(@PathVariable Integer id) {
		 empService.delete(id);
	}
	
//	@PutMapping("/emp/{id}")
//	public Employee deleteEmp(@PathVariable Integer id,@RequestBody Employee emp) {
//		 empService.update(id);
//	}

}
