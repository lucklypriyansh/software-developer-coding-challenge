package com.traderev.auction.model;

import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Auctioneer")
public class Auctioneer {

	@Id
	private String auctioneerId;
	private String auctionId;
	private TreeSet<Bid> bids= new TreeSet<>();
	public String getAuctioneerId() {
		return auctioneerId;
	}
	public void setAuctioneerId(String auctioneerId) {
		this.auctioneerId = auctioneerId;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public TreeSet<Bid> getBids() {
		return bids;
	}
	public void setBids(TreeSet<Bid> bids) {
		this.bids = bids;
	}
	

	

}
