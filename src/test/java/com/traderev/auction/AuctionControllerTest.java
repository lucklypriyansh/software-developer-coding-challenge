package com.traderev.auction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traderev.auction.constants.AuctionStatus;
import com.traderev.auction.controller.AuctionController;
import com.traderev.auction.dto.AuctionResponseDto;
import com.traderev.auction.mapper.ObjectMappers;
import com.traderev.auction.model.Auction;
import com.traderev.auction.model.Auctioneer;
import com.traderev.auction.model.Bid;
import com.traderev.auction.model.Car;
import com.traderev.auction.model.User;
import com.traderev.auction.model.Vehical;
import com.traderev.auction.repository.AuctionRepository;
import com.traderev.auction.repository.AuctioneerRepository;
import com.traderev.auction.repository.CarRepository;
import com.traderev.auction.repository.UserRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuctionController.class, secure = false)
public class AuctionControllerTest {

	@MockBean
	AuctionRepository auctionRepository;

	@Autowired
	ObjectMapper objectMapperJson;

	@MockBean
	ObjectMappers objectMappers;

	@MockBean
	CarRepository carRepository;

	@MockBean
	UserRepository userRepository;

	@MockBean
	AuctioneerRepository auctioneerRepository;

	private String AUCTIONMANAGEMENTURL = "/Auction-Controller";

	@Test
	public void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateAuction() throws Exception {

		Auction auction = createDummyAuction();
		Vehical vehical = createDummyVehical();

		when(auctionRepository.findByVehicalId(Mockito.anyString())).thenReturn(null);

		when(carRepository.findByVehicalId(Mockito.anyString())).thenReturn(vehical);

		when(auctionRepository.save(any(Auction.class))).thenReturn(auction);
		when(objectMappers.convertAuctionToDto(any(Auction.class))).thenReturn(createDummyAuctionResponseDto());

		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Auction-Controller/Auctions")
				.accept(MEDIA_TYPE_JSON_UTF8).content(objectMapperJson.writeValueAsString(auction))
				.contentType(MEDIA_TYPE_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		AuctionResponseDto output = objectMapperJson.readValue(result.getResponse().getContentAsString(),
				AuctionResponseDto.class);
		System.out.println(output.getAuctionId());
		Assert.assertTrue(output.getAuctionId().equals(auction.getAuctionId()));
		Assert.assertTrue(output.getVehical().getVehicalId().equals(auction.getVehicalId()));

		verify(carRepository, times(1)).findByVehicalId(Mockito.anyString());
		verify(auctionRepository, times(1)).save(any(Auction.class));
		verify(auctionRepository, times(1)).save(any(Auction.class));
		verify(objectMappers, times(1)).convertAuctionToDto(any(Auction.class));

	}

	@Test
	public void testCreateAuctionWhenAuctionAlreadyThereForVehical() throws Exception {
		Auction auction = createDummyAuction();
		Vehical vehical = createDummyVehical();

		when(auctionRepository.findByVehicalId(Mockito.anyString())).thenReturn(new Auction());
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Auction-Controller/Auctions")
				.accept(MEDIA_TYPE_JSON_UTF8).content(objectMapperJson.writeValueAsString(auction))
				.contentType(MEDIA_TYPE_JSON_UTF8);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    assertEquals(result.getResponse().getStatus(),422);
	    verify(auctionRepository,times(1)).findByVehicalId(Mockito.anyString());

	}
	@Test
	public void testCreateAuctionWhenVehicalInvalidVehicalId() throws Exception {
		Auction auction = createDummyAuction();

		auction.setVehicalId(null);
		when(auctionRepository.findByVehicalId(Mockito.anyString())).thenReturn(new Auction());
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Auction-Controller/Auctions")
				.accept(MEDIA_TYPE_JSON_UTF8).content(objectMapperJson.writeValueAsString(auction))
				.contentType(MEDIA_TYPE_JSON_UTF8);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    assertEquals(result.getResponse().getStatus(),422);

	}
	
	@Test
	public void testStartAuction() throws Exception {

		Auction auction = createDummyAuction();

		when(auctionRepository.findOne(Mockito.anyString())).thenReturn(auction);
		when(auctioneerRepository.save(any(Auctioneer.class))).thenReturn(createDummyAuctioneer());

		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(AUCTIONMANAGEMENTURL + "/Auctions/Start/{auctionId}", "12");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Auctioneer output = objectMapperJson.readValue(result.getResponse().getContentAsString(), Auctioneer.class);

		verify(auctionRepository, times(1)).findOne(Mockito.anyString());
		verify(auctionRepository, times(1)).save(any(Auction.class));

		verify(auctioneerRepository, times(1)).save(any(Auctioneer.class));
		Assert.assertTrue(output.getAuctionId().equals(auction.getAuctionId()));
		Assert.assertTrue(output.getAuctioneerId().equals("auctioneer1"));

	}

	@Test
	public void testStartAuctionWhenAuctionNotFound() throws Exception {

		Auction auction = createDummyAuction();
		when(auctionRepository.findOne(Mockito.anyString())).thenReturn(null);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(AUCTIONMANAGEMENTURL + "/Auctions/Start/{auctionId}", "12");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		verify(auctionRepository, times(1)).findOne(Mockito.anyString());
		assertEquals(result.getResponse().getStatus(), 404);
	}

	@Test
	public void testEndAuction() throws Exception {

		Auction auction = createDummyAuction();

		AuctionResponseDto auctionResponseDto = createDummyAuctionResponseDto();
		auctionResponseDto.setStatus(AuctionStatus.ENDED);

		when(auctionRepository.findOne(Mockito.anyString())).thenReturn(auction);

		when(objectMappers.convertAuctionToDto(any(Auction.class))).thenReturn(auctionResponseDto);
		when(auctionRepository.save(any(Auction.class))).thenReturn(auction);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(AUCTIONMANAGEMENTURL + "/Auctions/End/{auctionId}",
				"12");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		AuctionResponseDto output = objectMapperJson.readValue(result.getResponse().getContentAsString(),
				AuctionResponseDto.class);
		verify(objectMappers, times(1)).convertAuctionToDto(any(Auction.class));
		verify(auctionRepository, times(1)).save(any(Auction.class));

		Assert.assertTrue(output.getAuctionId().equals(auction.getAuctionId()));
		Assert.assertTrue(output.getStatus().equals(AuctionStatus.ENDED));

	}

	@Test
	public void testgetAuctionInfo() throws Exception {

		Auction auction = createDummyAuction();

		when(auctioneerRepository.findByAuctionId(anyString())).thenReturn(createDummyAuctioneer());
		when(objectMappers.convertAuctionToDto(any(Auctioneer.class))).thenReturn(createDummyAuctionResponseDto());

		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(AUCTIONMANAGEMENTURL + "/Auctions/{auctionId}",
				"12");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		AuctionResponseDto output = objectMapperJson.readValue(result.getResponse().getContentAsString(),
				AuctionResponseDto.class);

		verify(auctioneerRepository, times(1)).findByAuctionId(anyString());

		verify(objectMappers, times(1)).convertAuctionToDto(any(Auctioneer.class));

		Assert.assertTrue(output.getAuctionId().equals(auction.getAuctionId()));
		Assert.assertTrue(output.getOwnerName().equals("Owner1"));

	}
	
	@Test
	public void testgetAuctionInfoWhenNotFound() throws Exception {

		Auction auction = createDummyAuction();

		when(auctioneerRepository.findByAuctionId(anyString())).thenReturn(null);
			MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(AUCTIONMANAGEMENTURL + "/Auctions/{auctionId}",
				"12");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getStatus(),404);
		verify(auctioneerRepository, times(1)).findByAuctionId(anyString());

	
	}

	@Test
	public void testgetAllAuctions() throws Exception {

		List<Auction> auctionList = createDummyAuctionList();

		when(auctionRepository.findAll()).thenReturn(auctionList);
		when(objectMappers.convertAuctionToDto(any(Auction.class))).thenReturn(createDummyAuctionResponseDto());

		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(AUCTIONMANAGEMENTURL + "/Auctions");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		List<AuctionResponseDto> output = objectMapperJson.readValue(result.getResponse().getContentAsString(),
				List.class);

		verify(auctionRepository, times(1)).findAll();

		verify(objectMappers, times(4)).convertAuctionToDto(any(Auction.class));

		assertEquals(output.size(), 4);

	}

	@Test
	public void testgetWinningBids() throws Exception {

		Auctioneer dummyAuctioneer = mock(Auctioneer.class);
		when(auctioneerRepository.findByAuctionId(anyString())).thenReturn(dummyAuctioneer);
		when(dummyAuctioneer.getBids()).thenReturn(getBids());

		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(AUCTIONMANAGEMENTURL + "/Auctions/Bid/Winning/{auctionId}","121");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Bid bid = objectMapperJson.readValue(result.getResponse().getContentAsString(),
				Bid.class);

	}

	private User creatDummyUser() {
		User user = new User();
		user.setId("Owner1");
		user.setUsername("John");
		user.setUserPassword("Welcome@1");
		return user;
	}

	private TreeSet<Bid> getBids() {
	
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
	private Vehical createDummyVehical() {
		Car vehical = new Car();
		vehical.setType("SEDAN");
		vehical.setVehicalId("Vehical1");
		vehical.setCarName("Porsche");
		vehical.setOwnerId("Owner1");
		vehical.setStatus("NONE");
		return vehical;
	}

	private Auction createDummyAuction() {
		Auction auction = new Auction();
		auction.setAuctionId("12");
		auction.setEndTime(new Date());
		auction.setEndTime(new Date());
		auction.setVehicalId("Vehical1");
		return auction;
	}

	private List<Auction> createDummyAuctionList() {

		ArrayList<Auction> listofAuctionns = new ArrayList<>();

		listofAuctionns.add(createDummyAuction());
		listofAuctionns.add(createDummyAuction());
		listofAuctionns.add(createDummyAuction());
		listofAuctionns.add(createDummyAuction());
		return listofAuctionns;
	}

	private AuctionResponseDto createDummyAuctionResponseDto() {
		AuctionResponseDto auction = new AuctionResponseDto();
		auction.setAuctionId("12");
		auction.setEndTime(new Date());
		auction.setEndTime(new Date());
		auction.setVehical(createDummyVehical());
		auction.setOwnerName("Owner1");
		return auction;
	}

	private List<AuctionResponseDto> createDummyAuctionResponseDtoList() {
		List<AuctionResponseDto> auctionResponseDtoList = new ArrayList<>();
		auctionResponseDtoList.add(createDummyAuctionResponseDto());
		auctionResponseDtoList.add(createDummyAuctionResponseDto());
		auctionResponseDtoList.add(createDummyAuctionResponseDto());

		auctionResponseDtoList.add(createDummyAuctionResponseDto());
		return auctionResponseDtoList;
	}

	private Auctioneer createDummyAuctioneer() {
		Auctioneer auctioneer = new Auctioneer();
		auctioneer.setAuctionId("12");
		auctioneer.setAuctioneerId("auctioneer1");
		;
		return auctioneer;
	}

}
