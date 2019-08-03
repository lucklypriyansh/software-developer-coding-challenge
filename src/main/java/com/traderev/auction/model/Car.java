package com.traderev.auction.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vehical")

public class Car extends Vehical {

	String ownerId;
	String carName;
	String status;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}