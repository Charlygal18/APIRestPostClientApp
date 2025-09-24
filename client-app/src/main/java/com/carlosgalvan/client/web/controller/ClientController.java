package com.carlosgalvan.client.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosgalvan.client.web.Exception.ClientException;
import com.carlosgalvan.client.web.entity.Client;
import com.carlosgalvan.client.web.service.ClientServiceImp;

@RestController
@RequestMapping("/api")
public class ClientController {

	@Autowired
	private ClientServiceImp clientServiceImp;
	
	@PostMapping("/v1/create-client")
	public ResponseEntity<?> createClient(@RequestBody Client client){
		
		try {
		
			Client savedClient = clientServiceImp.saveClient(client);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
		}
		catch (ClientException e) {
			return ResponseEntity
                .badRequest()
                .body(e.getMessage());
		}
	}
}
