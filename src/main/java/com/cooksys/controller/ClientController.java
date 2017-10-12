package com.cooksys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.ClientDto;
import com.cooksys.embedded.Credentials;
import com.cooksys.pojo.ClientData;
import com.cooksys.service.ClientService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

//	@RequestMapping
//	public Set<ClientDto> getClients() {
//		return clientService.findClients();
//	}

	@PostMapping
	public ClientDto createClient(@RequestBody ClientData clientData, HttpServletResponse response) {
		if (!validClientData(clientData)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		ClientDto clientDto = clientService.findByUserName(clientData.getUserName());
		if (clientDto != null && clientService.userNameExists(clientDto.getUserName())) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		return clientService.create(clientData);
	}

	@GetMapping("/@{userName}")
	public ClientDto findByUserName(@RequestParam String userName, HttpServletResponse response) {

		// System.out.println("\n\n\n\n\nclient.isDeleted = " +
		// clientService.userNameExists(userName) + "\n\n\n\n\n");
		if (!clientService.userNameExists(userName)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return clientService.findByUserName(userName);
	}
	
	@PostMapping("/signIn")
	public boolean signIn(@RequestBody Credentials credentials){
		if (!clientService.userNameExists(credentials.getUserLogin())){
			return false;
		}
		return clientService.validatePassword(credentials);
	}

	@PostMapping("/@{userName}")
	public ClientDto updateClientProfile(@RequestBody ClientData clientData, HttpServletResponse response) {
		if (!validClientData(clientData)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		System.out.println("\n\n\n\n\nvalidClient(clientData) = " + validClient(clientData) + "\n\n\n\n\n");
		if (!validClient(clientData)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return clientService.updateClient(clientData);
	}

//	@RequestMapping("/@{userName}")
//	public ClientDto deleteClient(@RequestBody Credentials credentials, HttpServletResponse response) {
//		if (!validClient(credentials)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return clientService.deleteClient(credentials.getUserLogin());
//	}

//	@RequestMapping("/@{userName}/follow")
//	public void followClient(@RequestParam String userName, @RequestBody Credentials followerCred,
//			HttpServletResponse response) {
//		if (!validClient(followerCred) || !validClient(userName)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return;
//		}
//		clientService.follow(followerCred.getUserLogin(), userName);
//	}

//	@RequestMapping("@{username}/unfollow")
//	public void unFollowClient(@RequestParam String username, @RequestBody Credentials followerCred,
//			HttpServletResponse response) {
//		if (!validClient(followerCred) || !validClient(username)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return;
//		}
//		clientService.unFollow(followerCred.getUserLogin(), username);
//	}

//	@RequestMapping("/@{username}/feed")
//	public List<TweetDto> getFeed(@RequestParam String username, HttpServletResponse response) {
//		if (!clientService.userNameExists(username)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return clientService.getFeed(username);
//	}

//	@RequestMapping("/@{username}/tweets")
//	public List<TweetDto> getTweets(@RequestParam String username, HttpServletResponse response) {
//		if (!clientService.userNameExists(username)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return clientService.getTweets(username);
//	}

//	@RequestMapping("/@{username}/mentions")
//	public List<TweetDto> getMentions(@RequestParam String username, HttpServletResponse response) {
//		if (!clientService.userNameExists(username)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return clientService.getMentions(username);
//	}

//	@RequestMapping("/@{userName}/followers")
//	public Set<ClientDto> getFollowers(@RequestParam String userName, HttpServletResponse response) {
//		if (!clientService.userNameExists(userName)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return clientService.getFollowers(userName);
//	}

//	@RequestMapping("/@{userName}/following")
//	public Set<ClientDto> getFollowing(@RequestParam String userName, HttpServletResponse response) {
//		if (!clientService.userNameExists(userName)) {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return clientService.getFollowing(userName);
//	}

	private boolean validClient(ClientData clientData) {
		String userName = clientData.getUserName();
		if (!clientService.userNameExists(userName))
			return false;
		return clientService.validatePassword(clientData);
	}

	public boolean validClient(Credentials credentials) {
		String userName = credentials.getUserLogin();
		if (!clientService.userNameExists(userName))
			return false;
		return clientService.validatePassword(credentials);
	}

	public boolean validClient(String userName) {
		if (!clientService.userNameExists(userName))
			return false;
		return true;
	}

	private boolean validClientData(ClientData clientData) {
		if (clientData.getProfile().getEmail() == null || clientData.getProfile().getEmail().equals(""))
			return false;
		if (!validCredentials(clientData.getCredentials()))
			return false;
		return true;
	}

	private boolean validCredentials(Credentials credentials) {
		if (credentials.getUserLogin() == null || credentials.getUserLogin().equals(""))
			return false;
		if (credentials.getPassword() == null || credentials.getPassword().equals(""))
			return false;
		return true;
	}

}
