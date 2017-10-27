package com.alikaraca.springboot.Service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alikaraca.springboot.Model.Role;
import com.alikaraca.springboot.Model.User;
import com.alikaraca.springboot.Repository.RoleRepository;
import com.alikaraca.springboot.Repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder parolaKripto;
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
		
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(parolaKripto.encode(user.getPassword()));
		user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
