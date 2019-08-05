package com.traderev.auction.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="User")

public class User {

	@Id
	private String id;
	
	private String username;
	
	

	private String userPassword;
	
	private List<String> participatingAuctions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserPassword() {
		return userPassword;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<String> getParticipatingAuctions() {
		return participatingAuctions;
	}

	public void setParticipatingAuctions(List<String> participatingAuctions) {
		this.participatingAuctions = participatingAuctions;
	}
	
	
	
}
