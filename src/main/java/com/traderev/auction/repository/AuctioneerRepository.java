package com.traderev.auction.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.User;

@Repository
public interface AuctioneerRepository extends MongoRepository<Auctioneer, String> {

	
	public Auctioneer findByAuctionId(String auctionId);
	
	public int countByAuctionId(String auctionId);
	
	
}
