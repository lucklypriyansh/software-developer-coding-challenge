package com.traderev.auction.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="User")
@Data
public class User {

	@Id
	private String email;
	
	private String userPassword;
	private List<String> participatingAuctions;
	
	
	
}
