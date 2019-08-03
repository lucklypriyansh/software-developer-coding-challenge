package com.traderev.auction.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.traderev.auction.constants.AuctionStatus;




@Document(collection = "Auction")

public class Auction {

	@Id
	private String auctionId;
	private String vehicalId;
	private Date startTime;
	private Date endTime;
	private AuctionStatus status;
	private List<String> registeredUsers;

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getVehicalId() {
		return vehicalId;
	}

	public void setVehicalId(String vehicalId) {
		this.vehicalId = vehicalId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public AuctionStatus getStatus() {
		return status;
	}

	public void setStatus(AuctionStatus status) {
		this.status = status;
	}

	public List<String> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(List<String> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

}
