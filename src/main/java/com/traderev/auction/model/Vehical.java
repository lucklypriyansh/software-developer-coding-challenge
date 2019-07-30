package com.traderev.auction.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Vehical")
@Data
public class Vehical {

	String type;
	String vehicalId;

	public Vehical(String type, String vehicalid) {
		setType(type);
		setVehicalId(vehicalid);
	}
}
