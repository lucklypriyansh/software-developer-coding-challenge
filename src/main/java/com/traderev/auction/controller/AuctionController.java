package com.traderev.auction.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.auction.constants.AuctionStatus;
import com.traderev.auction.dto.AuctionResponseDto;
import com.traderev.auction.exception.AuctionInvalidState;
import com.traderev.auction.exception.NotFoundException;
import com.traderev.auction.mapper.ObjectMappers;
import com.traderev.auction.model.Auction;
import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Bid;
import com.traderev.auction.model.Vehical;
import com.traderev.auction.repository.AuctionRepository;
import com.traderev.auction.repository.AuctioneerRepository;
import com.traderev.auction.repository.CarRepository;
import com.traderev.auction.repository.UserRepository;

@RestController
@RequestMapping("/Auction-Controller")
public class AuctionController {

	@Autowired
	AuctionRepository auctionRepository;

	@Autowired
	ObjectMappers objectMappers;

	@Autowired
	CarRepository carRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuctioneerRepository auctioneerRepository;

	@PostMapping("/Auctions")
	public AuctionResponseDto createAuction(@RequestBody Auction auction) {

		if (auctionRepository.findByVehicalId(auction.getVehicalId()) != null) {
			throw new AuctionInvalidState("Auctin already  present for same vehical id");
		}
		if (auction.getVehicalId() == null || carRepository.findByVehicalId(auction.getVehicalId()) == null) {
			throw new AuctionInvalidState("Invalid vehical id");
		}
		auction.setAuctionId(UUID.randomUUID().toString());
		return objectMappers.convertAuctionToDto(auctionRepository.save(auction));
	}

	@PostMapping("/Auctions/Start/{auctionId}")
	public Auctioneer startAuction(@PathVariable("auctionId") String auctionId) {
		Auction auction = auctionRepository.findOne(auctionId);
		auction.setStatus(AuctionStatus.STARTED);
		auctionRepository.save(auction);
		if (auction != null) {
			Auctioneer auctioneer = new Auctioneer();
			auctioneer.setAuctioneerId(UUID.randomUUID().toString());
			auctioneer.setAuctionId(auction.getAuctionId());
			return auctioneerRepository.save(auctioneer);
		}
		throw new NotFoundException("InvalidAuctionId");
	}

	@PostMapping("/Auctions/End/{auctionId}")
	public AuctionResponseDto endAuction(@PathVariable("auctionId") String auctionId) {
		Auction auction = auctionRepository.findOne(auctionId);
		auction.setStatus(AuctionStatus.ENDED);
		return objectMappers.convertAuctionToDto(auctionRepository.save(auction));

	}

	@GetMapping("/Auctions/{auctionId}")
	public AuctionResponseDto getAuctionInfo(@PathVariable("auctionId") String auctionId) {

		Auctioneer auctioneer = auctioneerRepository.findByAuctionId(auctionId);
		if (auctioneer == null)
			throw new NotFoundException("Auction is not started no auctioneer assigned");

		return objectMappers.convertAuctionToDto(auctioneer);

	}

	@GetMapping("/Auctions")
	public List<AuctionResponseDto> getAllAuctions() {

		return auctionRepository.findAll().stream().map(auction -> {
			return objectMappers.convertAuctionToDto(auction);
		}).collect(Collectors.toList());

	}

	@GetMapping("/Auctions/Bid/Winning/{auctionId}")
	public Bid getWinningBids(@PathVariable("auctionId") String auctionId) {

		return auctioneerRepository.findByAuctionId(auctionId).getBids().first();

	}

}
