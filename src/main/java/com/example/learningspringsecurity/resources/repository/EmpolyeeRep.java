package com.example.learningspringsecurity.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningspringsecurity.resources.model.entity.Employee;

public interface EmpolyeeRep extends JpaRepository<Employee, Integer> {

}
