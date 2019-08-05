package com.traderev.auction.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Car;
import com.traderev.auction.model.Vehical;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

	Vehical findByVehicalId(String vehicalId);

}
