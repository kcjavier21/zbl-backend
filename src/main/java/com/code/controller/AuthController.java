package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.controller.JwtUtility;
import com.code.services.UserDao;
import com.code.model.User;

@RestController
public class AuthController {
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/home")
	public String home() {
		return "This is Home page";
	}
	
	@RequestMapping(value = "/home/another", method = RequestMethod.GET)
	public String homeAnother() {
		return "This is Another Home page";
	}
	
	
	@RequestMapping(value = "/get-token", method = RequestMethod.GET)
	public String getToken(Model model, @RequestParam("username") String username) {
		User user = new User();
		
		try {
			user = userDao.getUserByUsername(username);
			if (user != null) return jwtUtility.generateToken(user);
			else return "User not found :(";
		}
		catch (Exception ex) {
			System.out.println(ex);
			return "User not found :(";
		}
	}
	
	@RequestMapping(value = "/decode-token", method = RequestMethod.GET)
	public String decodeToken(Model model, @RequestParam("token") String token) {
		System.out.println(jwtUtility.getUsernameFromToken(token));
		return jwtUtility.getUsernameFromToken(token);
	}
}
