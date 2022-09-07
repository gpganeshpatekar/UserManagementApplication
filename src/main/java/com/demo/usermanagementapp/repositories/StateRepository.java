package com.demo.usermanagementapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.usermanagementapp.entities.StateMasterEntity;

@Repository
public interface StateRepository extends JpaRepository<StateMasterEntity, Integer>{
	
	public List<StateMasterEntity> findByCountryId(Integer countryId);
	
	
}
