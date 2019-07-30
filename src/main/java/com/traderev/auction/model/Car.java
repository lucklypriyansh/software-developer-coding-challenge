package com.traderev.auction.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="Vehical")
@Data
public class Car extends Vehical
{

	User owner;
	String carName;
	String status;

	

}