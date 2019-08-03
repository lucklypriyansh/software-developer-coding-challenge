package com.traderev.auction.model;

public class Bid implements Comparable<Bid> {

	private String bidId;
	private Integer bidAmount;
	private String auctionId;

	@Override
	public int compareTo(Bid o) {

		return this.bidAmount.compareTo(o.bidAmount);
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
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
