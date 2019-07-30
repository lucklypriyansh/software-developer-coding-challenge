package com.traderev.auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traderev.auction.model.User;
import com.traderev.auction.repository.UserRepository;

@Service
public class UserManagementService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User addUser(User user) {

		return userRepository.save(user);
	}

	public User findOne(String userId) {
		
		return userRepository.findOne(userId);
	}

}
