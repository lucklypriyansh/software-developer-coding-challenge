package com.traderev.auction.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.auction.model.Car;
import com.traderev.auction.repository.CarRepository;

@RestController
@RequestMapping("/Vehical-Management")
public class VehicalController {

	@Autowired
	CarRepository carrepository;

	@PostMapping("/Vehical")
	public Car createCar(@RequestBody Car car) {
		car.setVehicalId(UUID.randomUUID().toString());
		return carrepository.save(car);
	}
	
	@GetMapping("/Vehical")
	public List<Car> getAllVehicals() {
		
		return carrepository.findAll();
	}

}
