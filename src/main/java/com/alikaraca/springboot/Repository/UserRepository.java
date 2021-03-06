package com.alikaraca.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alikaraca.springboot.Model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
