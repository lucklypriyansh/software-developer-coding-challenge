package com.traderev.auction.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vehical")
public class Vehical {
	
	String type;
	String vehicalId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVehicalId() {
		return vehicalId;
	}

	public void setVehicalId(String vehicalId) {
		this.vehicalId = vehicalId;
	}

}
