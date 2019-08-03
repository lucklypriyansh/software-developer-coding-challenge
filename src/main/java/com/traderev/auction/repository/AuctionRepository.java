package com.traderev.auction.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.traderev.auction.constants.AuctionStatus;
import com.traderev.auction.model.Auction;

@Repository
public interface AuctionRepository extends MongoRepository<Auction, String> {

	
	public List<Auction> findByStatus(AuctionStatus status);
	
	
}
