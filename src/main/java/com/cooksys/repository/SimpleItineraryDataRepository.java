package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Client;
import com.cooksys.entity.SimpleItineraryData;

public interface SimpleItineraryDataRepository extends JpaRepository<SimpleItineraryData, Long> {

	List<SimpleItineraryData> findByTraveller(Client client);

	SimpleItineraryData findById(Long id);

	
}
