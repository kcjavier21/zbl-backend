package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.User;
import com.code.services.UserDao;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/get/all-users", method = RequestMethod.GET)
	public List<User> getAllPlayersInATeam(Model model, @RequestParam("userId") int userId) {
		List<User> listOfUsers = userDao.getAllUsers();
		return listOfUsers;
	}
	
	@RequestMapping(value = "/get/single-user-by-id", method = RequestMethod.GET)
	public ResponseEntity<User> getSingleUserById(Model model, @RequestParam("id") int id) {
		User user = new User();
		
		try {
			user = userDao.getSingleUser(id);	
			if (user != null) return ResponseEntity.status(200).body(user);
			else return ResponseEntity.status(404).body(user);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(user);
		}
	}
	
	@RequestMapping(value = "/get/single-user-by-username", method = RequestMethod.GET)
	public ResponseEntity<User> getSingleUserByUsername(Model model, @RequestParam("username") String username) {
		User user = new User();
		
		try {
			user = userDao.getUserByUsername(username);	
			if (user != null) return ResponseEntity.status(200).body(user);
			else return ResponseEntity.status(404).body(user);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(user);
		}
	}
	
	@RequestMapping(value = "/post/register-new-user", method = RequestMethod.GET)
	public ResponseEntity<String> registerNewUser(@RequestBody User user) {
		System.out.println("Hello");
		ResponseEntity<String> response = userDao.registerNewUser(user);
		return response;
	}
	
	@RequestMapping(value = "/update/user-by-id", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(Model model, @RequestParam("id") int id, @RequestBody User user) throws Exception {
		User userToUpdate = userDao.getSingleUser(id);	
		if (userToUpdate == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = userDao.updateUser(id, user);
		return response;
	}
	
	@RequestMapping(value = "/delete/user-by-id", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(Model model, @RequestParam("id") int id) throws Exception {
		User userToDelete = userDao.getSingleUser(id);
		if (userToDelete == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = userDao.deleteUser(id);
		return response;
	}
}
