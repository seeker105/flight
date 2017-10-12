package com.cooksys.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByUserName(String userName);
	
//	Set<Client> findByFollowersAndDeleted(Client client, boolean deleted);
//
//	Set<Client> findByFollowingAndDeleted(Client client, boolean deleted);
//
//	Client findByIdAndFollowing(Integer id, Client followedClient);


}
