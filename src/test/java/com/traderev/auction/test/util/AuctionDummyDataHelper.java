package com.traderev.auction.test.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import com.traderev.auction.dto.AuctionResponseDto;
import com.traderev.auction.model.Auction;
import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Bid;
import com.traderev.auction.model.Car;
import com.traderev.auction.model.User;
import com.traderev.auction.model.Vehical;

/**
 * Could contain the file that read data from json and creates dummy data
 * 
 * @author pnigam
 *
 */
public class AuctionDummyDataHelper extends DummyDataHelper {

	/**
	 * Helper could load file and create dummy objects from file
	 * 
	 * @param filePath
	 */
	public AuctionDummyDataHelper(String filePath) {
		loadDataFile(filePath);
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public User creatDummyUser() {

		User user = new User();
		user.setId("Owner1");
		user.setUsername("John");
		user.setUserPassword("Welcome@1");
		return user;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public TreeSet<Bid> getBids() {

		TreeSet<Bid> bids = new TreeSet<>();
		Bid b = new Bid();
		b.setAuctionId("owner1");
		b.setBidAmount(100);
		b.setUserId("user1");
		bids.add(b);

		Bid b2 = new Bid();
		b2.setAuctionId("owner2");
		b2.setBidAmount(50);
		b2.setUserId("user2");
		bids.add(b2);
		return bids;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public Vehical createDummyVehical() {
		Car vehical = new Car();
		vehical.setType("SEDAN");
		vehical.setVehicalId("Vehical1");
		vehical.setCarName("Porsche");
		vehical.setOwnerId("Owner1");
		vehical.setStatus("NONE");
		return vehical;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public Auction createDummyAuction() {
		Auction auction = new Auction();
		auction.setAuctionId("12");
		auction.setEndTime(new Date());
		auction.setEndTime(new Date());
		auction.setVehicalId("Vehical1");
		return auction;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public List<Auction> createDummyAuctionList() {

		ArrayList<Auction> listofAuctionns = new ArrayList<>();
		listofAuctionns.add(createDummyAuction());
		listofAuctionns.add(createDummyAuction());
		listofAuctionns.add(createDummyAuction());
		listofAuctionns.add(createDummyAuction());
		return listofAuctionns;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public AuctionResponseDto createDummyAuctionResponseDto() {
		AuctionResponseDto auction = new AuctionResponseDto();
		auction.setAuctionId("12");
		auction.setEndTime(new Date());
		auction.setEndTime(new Date());
		auction.setVehical(createDummyVehical());
		auction.setOwnerName("Owner1");
		return auction;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public List<AuctionResponseDto> createDummyAuctionResponseDtoList() {
		List<AuctionResponseDto> auctionResponseDtoList = new ArrayList<>();
		auctionResponseDtoList.add(createDummyAuctionResponseDto());
		auctionResponseDtoList.add(createDummyAuctionResponseDto());
		auctionResponseDtoList.add(createDummyAuctionResponseDto());

		auctionResponseDtoList.add(createDummyAuctionResponseDto());
		return auctionResponseDtoList;
	}

	/**
	 * for time being data is created manually but will take file to create test
	 * data
	 * 
	 * @return
	 */
	public Auctioneer createDummyAuctioneer() {
		Auctioneer auctioneer = new Auctioneer();
		auctioneer.setAuctionId("12");
		auctioneer.setAuctioneerId("auctioneer1");

		return auctioneer;
	}
}
