package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.code.model.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getSingleUser(int id);
	public ResponseEntity<String> registerNewUser(User user);
	public ResponseEntity<String> updateUser(int id, User user);
	public ResponseEntity<String> deleteUser(int id);
	public User getUserByUsername(String username);
}
