package com.traderev.auction.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.auction.model.LoginRequest;
import com.traderev.auction.model.LoginResponse;
import com.traderev.auction.model.User;
import com.traderev.auction.repository.UserRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/authenticate")
	public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) {

		LoginResponse loginResponse = new LoginResponse();

		User user = userRepository.findbyIdAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
		JwtBuilder builder = Jwts.builder().setSubject(user.getId()).claim("userId", user.getId())
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "MySecretKey");
		loginResponse.setAuthtoken(builder.compact());
		return loginResponse;
	}

}
