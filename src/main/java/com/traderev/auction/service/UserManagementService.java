package com.traderev.auction.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Bid;
import com.traderev.auction.model.User;
import com.traderev.auction.repository.AuctioneerRepository;
import com.traderev.auction.repository.UserRepository;

@Service
public class UserManagementService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuctioneerRepository auctioneerRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User addUser(User user) {
		
		return userRepository.save(user);
	}

	public User findOne(String userId) {

		return userRepository.findOne(userId);
	}

	public void placeBid(Bid bid) {

		bid.setBidId(UUID.randomUUID().toString());
		if (auctioneerRepository.countByAuctionId(bid.getAuctionId()) > 0) {

			// can use mongo template for optimised update
			Auctioneer auctioneer=auctioneerRepository.findByAuctionId(bid.getAuctionId());
			auctioneer.getBids().add(bid);
			auctioneerRepository.save(auctioneer);

		}

	}

}
