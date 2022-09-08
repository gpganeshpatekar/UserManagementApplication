package com.demo.usermanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.usermanagementapp.entities.UserAccountEntity;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {
	
	public UserAccountEntity findByEmailAndPassword(String email,String password);
	
	public UserAccountEntity findByEmail(String email);

}
