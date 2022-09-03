package com.demo.usermanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.usermanagementapp.entities.StateMasterEntity;

@Repository
public interface StateRepository extends JpaRepository<StateMasterEntity, Integer>{
	
	
}
