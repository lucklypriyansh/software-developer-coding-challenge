package com.traderev.auction.mapper;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.traderev.auction.dto.AuctionResponseDto;
import com.traderev.auction.dto.BidDto;
import com.traderev.auction.model.Auction;
import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Bid;
import com.traderev.auction.model.Car;
import com.traderev.auction.model.User;
import com.traderev.auction.repository.AuctionRepository;
import com.traderev.auction.repository.AuctioneerRepository;
import com.traderev.auction.repository.CarRepository;
import com.traderev.auction.repository.UserRepository;

@Component
public class ObjectMappers {
	@Autowired
	AuctionRepository auctionRepository;

	@Autowired
	CarRepository carRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuctioneerRepository auctioneerRepository;

	public AuctionResponseDto convertAuctionToDto(Auction auction) {
		AuctionResponseDto auctionReponse = new AuctionResponseDto();
		auctionReponse.setAuctionId(auction.getAuctionId());
		auctionReponse.setEndTime(auction.getEndTime());
		auctionReponse.setStartTime(auction.getStartTime());
		auctionReponse.setStatus(auction.getStatus());
		
		auctionReponse.setVehical(carRepository.findByVehicalId(auction.getVehicalId()));
		User owner = userRepository.findOne(((Car) auctionReponse.getVehical()).getOwnerId());
		auctionReponse.setOwnerName(owner != null ? owner.getUsername() : null);
		return auctionReponse;
	}

	public AuctionResponseDto convertAuctionToDto(Auctioneer auctioneer) {
		AuctionResponseDto auctionReponse = new AuctionResponseDto();
		auctionReponse.setAuctionId(auctioneer.getAuctionId());
		Auction auctionInfo = auctionRepository.findOne(auctioneer.getAuctionId());
		auctionReponse.setEndTime(auctionInfo.getEndTime());
		auctionReponse.setStartTime(auctionInfo.getStartTime());
		auctionReponse.setStatus(auctionInfo.getStatus());
		auctionReponse.setVehical(carRepository.findByVehicalId(auctionInfo.getVehicalId()));
		if(auctionReponse.getVehical()!=null)
		{
		User owner = userRepository.findOne( ((Car)(auctionReponse.getVehical())).getOwnerId());
		auctionReponse.setOwnerName(owner != null ? owner.getUsername() : null);	
		}
		auctionReponse.setBiddingInfo(auctioneer.getBids().stream().map(bid->convertBid(bid)).collect(Collectors.toList()));
		return auctionReponse;
	}

	public Bid convertBid(BidDto bidDto) {
		Bid bid = new Bid();
		bid.setAuctionId(bidDto.getAuctionId());
		bid.setBidAmount(bidDto.getBidAmount());
		bid.setBidId(UUID.randomUUID().toString());
		User user = userRepository.findOne(bidDto.getUserName());
		bid.setUserId(user.getId());
		return bid;
	}
	
	public BidDto convertBid(Bid bid) {
		BidDto biddto = new BidDto();
		biddto.setAuctionId(bid.getAuctionId());
		biddto.setBidAmount(bid.getBidAmount());
		biddto.setUserName(userRepository.findOne(bid.getUserId()).getUsername());
		return biddto;
	}
}
