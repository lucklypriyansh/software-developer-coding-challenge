package com.traderev.auction.dto;

public class BidDto {

	String userName;
	String password;
	private Integer bidAmount;
	private String auctionId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(Integer bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	
	
}
