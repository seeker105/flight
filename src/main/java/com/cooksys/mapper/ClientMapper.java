package com.cooksys.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.dto.ClientDto;
import com.cooksys.entity.Client;


@Mapper(componentModel="spring")
public interface ClientMapper {

	ClientDto toDto(Client client);
	
	Client fromDto(ClientDto clientDto);
	
	Set<ClientDto> toDtos(Set<Client> set);
	
	List<ClientDto> toDtos(List<Client> set);
}
