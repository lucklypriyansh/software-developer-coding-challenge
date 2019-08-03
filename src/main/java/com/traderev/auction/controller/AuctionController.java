package com.traderev.auction.controller;

import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.auction.constants.AuctionStatus;
import com.traderev.auction.exception.AuctionNotFoundException;
import com.traderev.auction.model.Auction;
import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Bid;
import com.traderev.auction.repository.AuctionRepository;
import com.traderev.auction.repository.AuctioneerRepository;

@RestController
@RequestMapping("/Auction-Controller")
public class AuctionController {

	@Autowired
	AuctionRepository auctionRepository;

	@Autowired
	AuctioneerRepository auctioneerRepository;

	@GetMapping("/Auctions/{status}")
	public List<Auction> getAllAuctions(@PathVariable("status") String status) {
		if (status == null || status.trim().equals(""))
			return auctionRepository.findAll();
		if (status.equalsIgnoreCase(AuctionStatus.STARTED.name()))
			return auctionRepository.findByStatus(AuctionStatus.STARTED);
		else if (status.equalsIgnoreCase(AuctionStatus.ENDED.name()))
			return auctionRepository.findByStatus(AuctionStatus.ENDED);
		return null;
	}

	@PostMapping("/Auctions")
	public Auction createAuction(@RequestBody Auction auction) {
		auction.setAuctionId(UUID.randomUUID().toString());
		return auctionRepository.save(auction);
	}

	@PostMapping("/Auctions/Start/{auctionId}")
	public Auctioneer startAuction(@PathVariable("auctionId") String auctionId) {

		Auction auction = auctionRepository.findOne(auctionId);
		if (auction != null) {
			Auctioneer auctioneer = new Auctioneer();
			auctioneer.setAuctioneerId(UUID.randomUUID().toString());
			auctioneer.setAuctionId(auction.getAuctionId());
			return auctioneerRepository.save(auctioneer);
		}
		throw new AuctionNotFoundException("InvalidAuctionId");
	}

	@PostMapping("/Auctions/End/{auctionId}")
	public Auction endAuction(@PathVariable("auctionId") String auctionId) {
		Auction auction = auctionRepository.findOne(auctionId);
		auction.setStatus(AuctionStatus.ENDED);
		return auctionRepository.save(auction);

	}

	@GetMapping("/Auctions/Bid/{auctionId}")
	public TreeSet<Bid> getAllBids(@PathVariable("auctionId") String auctionId) {

		return auctioneerRepository.findByAuctionId(auctionId).getBids();

	}
	
	@GetMapping("/Auctions")
	public List<Auction> getAllAuctions() {

		return auctionRepository.findAll();

	}
	
	@GetMapping("/Auctions/Bid/Winning/{auctionId}")
	public Bid getWinningBids(@PathVariable("auctionId") String auctionId) {

		return auctioneerRepository.findByAuctionId(auctionId).getBids().first();

	}

}
