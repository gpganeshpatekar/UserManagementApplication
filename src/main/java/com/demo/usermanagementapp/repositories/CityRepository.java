package com.demo.usermanagementapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.usermanagementapp.entities.CityMasterEntity;

@Repository
public interface CityRepository extends JpaRepository<CityMasterEntity, Integer> {
	
	public List<CityMasterEntity> findByStateId(Integer stateId);

}
