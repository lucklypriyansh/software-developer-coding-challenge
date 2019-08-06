package com.traderev.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.auction.constants.AuctionStatus;
import com.traderev.auction.dto.BidDto;
import com.traderev.auction.exception.AuthorisationException;
import com.traderev.auction.exception.BidException;
import com.traderev.auction.exception.UserNotFoundException;
import com.traderev.auction.mapper.ObjectMappers;
import com.traderev.auction.model.Auction;
import com.traderev.auction.model.Bid;
import com.traderev.auction.model.User;
import com.traderev.auction.repository.AuctionRepository;
import com.traderev.auction.repository.AuctioneerRepository;
import com.traderev.auction.service.UserManagementService;

@RestController
@RequestMapping("/User-Management")
public class UserController {

	@Autowired
	UserManagementService usermanagementService;

	@Autowired
	ObjectMappers objectMappers;

	@Autowired
	AuctionRepository auctionRepositoru;

	@GetMapping("/Users")
	public List<User> getAllUsers() {
		return usermanagementService.getAllUsers();
	}

	@PostMapping("/Users")
	public User addUser(@RequestBody User user) {
		if (usermanagementService.findOne(user.getId()) != null) {
			throw new UserNotFoundException("User with email id already exist");
		}
		return usermanagementService.addUser(user);
	}

	@GetMapping("/Users/{userId}")
	public User getUser(@PathVariable("userId") String userId) {

		return usermanagementService.findOne(userId);
	}

	@PostMapping("/Users/Bid")
	public void postBid(@RequestBody BidDto BidDto) {

		User user = usermanagementService.findOne(BidDto.getUserName());
		if (user == null)
			throw new UserNotFoundException("user id not present");
		if (!user.getUserPassword().equals(BidDto.getPassword()))
			throw new AuthorisationException("not valid password");
		Auction auction = auctionRepositoru.findOne(BidDto.getAuctionId());
		if (auction == null || auction.getStatus() != AuctionStatus.STARTED) {
			throw new BidException("Auction not present or not started please check");
		}

		Bid bid = objectMappers.convertBid(BidDto);
		usermanagementService.placeBid(bid);
	}

}
