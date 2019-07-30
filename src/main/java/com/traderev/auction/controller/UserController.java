package com.traderev.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.auction.exception.UserNotFoundException;
import com.traderev.auction.model.User;
import com.traderev.auction.service.UserManagementService;

@RestController
@RequestMapping("/User-Management")
public class UserController {

	@Autowired
	UserManagementService usermanagementService;

	@GetMapping("/Users")
	public List<User> getAllUsers() {
		return usermanagementService.getAllUsers();
	}

	@PostMapping("/Users")
	public User addUser(@RequestBody User user) {
		if (usermanagementService.findOne(user.getEmail()) == null) {
			throw new UserNotFoundException("User with email id not exist");
		}
		return usermanagementService.addUser(user);
	}

	@GetMapping("/Users/{userId}")
	public User addUser(@PathVariable("userId") String userId) {

		return usermanagementService.findOne(userId);
	}

}
