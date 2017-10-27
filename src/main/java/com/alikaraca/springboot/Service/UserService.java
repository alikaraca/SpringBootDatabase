package com.alikaraca.springboot.Service;


import com.alikaraca.springboot.Model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
