package com.cooksys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.SimpleItineraryData;
import com.cooksys.pojo.Flight;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightsController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping
	public ArrayList<Flight> getFlightList()
	{
		return flightService.getDailyFlightList();
	}

	@RequestMapping("/from/{origin}/to/{destination}")
	public List<Flight> get(@PathVariable String origin, @PathVariable String destination, HttpServletResponse response) {
		origin = origin.toUpperCase();
		destination = destination.toUpperCase();
		if (destination.equals(origin)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		return flightService.getFlights(origin, destination);

	}
	
	@RequestMapping("/all_flights_from/{origin}")
	public ArrayList<Flight> getFlightsFrom(@PathVariable String origin){
		origin = origin.toUpperCase();
		Flight nullFlight = new Flight(origin, origin, 0, 0);

		return flightService.getAllFlightsFrom(origin, nullFlight);
	}
	
	@RequestMapping("/book/{userName}")
	public SimpleItineraryData post(@PathVariable String userName, @RequestBody String intineraryString, HttpServletResponse response){
		return flightService.book(intineraryString, userName);
	}
	
	@RequestMapping("/getBookings/{userName}")
	public List<String> getBookings(@PathVariable String userName){
		return flightService.getBookings(userName);
	}
	
	@RequestMapping("/checkBookingExists/{id}")
	public boolean checkBookingExists(@PathVariable Long id){
		return flightService.checkBookingExists(id);
	}
}
