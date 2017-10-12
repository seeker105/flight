package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Client;
import com.cooksys.entity.SimpleItineraryData;
import com.cooksys.pojo.Flight;
import com.cooksys.repository.ClientRepository;
import com.cooksys.repository.SimpleItineraryDataRepository;

@Service
public class FlightService {
	private ArrayList<String> visitedCities;
	private final long MIN_LAYOVER = 1;
	private ArrayList<Flight> route;
	private ClientRepository clientRepository;
	private SimpleItineraryDataRepository simpleItineraryDataRepository;
//	private ItineraryRepository itineraryRepository;
	

	@Autowired
	FlightGenerator generator;


	public FlightService(ClientRepository clientRepository,
			SimpleItineraryDataRepository simpleItineraryDataRepository) {
		super();
		this.clientRepository = clientRepository;
		this.simpleItineraryDataRepository = simpleItineraryDataRepository;
	}

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=50000000)
	public void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
	}
	
	public List<Flight> getFlights(String origin, String destination) {
		visitedCities = new ArrayList<String>();
		Flight nullFlight = new Flight(origin, origin, 0, 0);
//		Flight flight1 = new Flight("NASHVILLE", "KNOXVILLE", 1, 2);
//		Flight flight2 = new Flight("KNOXVILLE", "CHATTANOOGA", 2, 5);
//		Flight flight3 = new Flight("CHATTANOOGA", "MEMPHIS", 1, 9);
//		Flight flight4 = new Flight("CHATTANOOGA", "KNOXVILLE", 1, 9);
//		flightList = new ArrayList<Flight>();
//		flightList.add(flight1);
//		flightList.add(flight2);
//		flightList.add(flight3);
//		flightList.add(flight4);
//		System.out.println(flightList);
//		return searchForFlights(origin, destination, nullFlight, route);
		
		route = new ArrayList<Flight>();
		searchForFlights(origin, destination, nullFlight);
		return route;
	}

	private boolean searchForFlights(String origin, String destination, Flight currentFlight) {
		String currentCity = currentFlight.getDestination();
		
		System.out.println(currentCity);
		System.out.println(visitedCities);
		if (visitedCities.contains(currentCity)){
			return false;
		}
		
		visitedCities.add(currentCity);
		
		if (currentCity.equals(destination)){
			return true;
		}
		
		ArrayList<Flight> edges = getAllFlightsFrom(currentCity, currentFlight);
		for (Flight f : edges){
			if (searchForFlights(origin, destination, f)){
				route.add(0, f);
				return true;
			}
		}
		return false;
	}
	
	@Transactional
	public SimpleItineraryData book(String itineraryString, String userName) {
		Client client = clientRepository.findByUserName(userName);
//		Itinerary itinerary = new Itinerary(flights, client);
//		Itinerary temp = itineraryRepository.save(itinerary);
//		if (temp != null && temp.getFlights() != null){
//			return temp.getFlights();
//		}
//		return new ArrayList<Flight>();
		
		SimpleItineraryData simpleData = new SimpleItineraryData(itineraryString, client);
		return simpleItineraryDataRepository.save(simpleData);
		
	}	

	public ArrayList<Flight> getAllFlightsFrom(String origin, Flight flight) {
		long departureTime = flight.getOffset() + flight.getFlightTime() + MIN_LAYOVER;
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for (Flight f : flightList) {

			// if flight is a child of origin and leaving later than the arriving flight
			if (f.getOrigin().equals(origin) && f.getOffset() >= departureTime) {
				flights.add(f);
			}
		}
		return flights;
	}

	public List<String> getBookings(String userName) {
		Client client = clientRepository.findByUserName(userName);
		List<SimpleItineraryData> dataList = simpleItineraryDataRepository.findByTraveller(client);
		List<String> result = new ArrayList<String>();
		for (SimpleItineraryData d : dataList){
			result.add(d.getItineraryString());
		}
		return result;
	}

	public boolean checkBookingExists(Long id) {
		SimpleItineraryData data = simpleItineraryDataRepository.findById(id);
		return data != null;
	}
}







//System.out.println("What is flight f ");
//System.out.println(f.getOrigin());
//System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
//System.out.println("what is route");
//System.out.println(route);








//private ArrayList<Flight> searchForFlights(String origin, String destination, Flight currentFlight, ArrayList<Flight> route) {
//String currentCity = currentFlight.getDestination();
//
//if (visitedCities.contains(currentCity)){
//	return route;
//}
//
//visitedCities.add(currentCity);
//
//if (currentCity.equals(destination)){
//	route.add(currentFlight);
//	return route;
//}
//
//ArrayList<Flight> edges = getAllFlightsFrom(origin, currentFlight);
//ArrayList<Flight> temp;
//for (Flight f : edges){
//	temp = searchForFlights(origin, destination, f, route);
//	if (temp.size() > 0){
//		temp.add(f);
//		return temp;
//	}
//}
//return route;
//}
