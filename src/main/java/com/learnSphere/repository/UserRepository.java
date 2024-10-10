package com.learnSphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnSphere.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	boolean existsByEmail(String email);
	
	Users getByEmail(String Email);

}
