package com.traderev.auction.dto;

import java.util.Date;
import java.util.List;

import com.traderev.auction.constants.AuctionStatus;
import com.traderev.auction.model.Vehical;

public class AuctionResponseDto {

	private String auctionId;
	private Date startTime;
	private Date endTime;
	private AuctionStatus status;
	private String ownerName;
	private Vehical vehical;
	private String imgurl;
	List<BidDto> biddingInfo;
	private String auctioneerId;
	
	
	public String getAuctioneerId() {
		return auctioneerId;
	}

	public void setAuctioneerId(String auctioneerId) {
		this.auctioneerId = auctioneerId;
	}



	public List<BidDto> getBiddingInfo() {
		return biddingInfo;
	}

	public void setBiddingInfo(List<BidDto> biddingInfo) {
		this.biddingInfo = biddingInfo;
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
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

	public Vehical getVehical() {
		return vehical;
	}

	public void setVehical(Vehical vehical) {
		this.vehical = vehical;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	

}
