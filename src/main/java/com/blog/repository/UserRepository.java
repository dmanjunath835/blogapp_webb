package com.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entites.User;

public interface UserRepository extends JpaRepository<User, Long>{
  Optional<User> findByUsername(String usrename);
  Optional<User> findByEmail(String email);
  Optional<User>findByUsernameOrEmail(String username,String email);
	 boolean existsByEmail(String email);
	 boolean existsByUsername(String username);
	
}
