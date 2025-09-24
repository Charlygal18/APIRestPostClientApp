package com.carlosgalvan.client.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosgalvan.client.web.Exception.ClientException;
import com.carlosgalvan.client.web.entity.Client;
import com.carlosgalvan.client.web.entity.ClientPhone;
import com.carlosgalvan.client.web.repository.ClientRepository;

@Service
public class ClientServiceImp {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client saveClient(Client client) throws ClientException {
		
		if(!validName(client)) {
			
			throw new ClientException("The name can't be null or empty");
		}
		
		if(!validAge(client)) {
			
			throw new ClientException("You must be over 18 years of age");
		}
		
		validPhoneNumbers(client);
		
		client.setZipCode(validZipCode(client));
		
		if (client.getPhones() != null) {
	        client.getPhones().forEach(phone -> phone.setClient(client));
	    }
		
		return clientRepository.save(client);
		
	}
	
	public boolean validName(Client client) {
		
		return client.getName() != null && !client.getName().isBlank();
		
	}
	
	public boolean validAge(Client client) {
		
		return client.getAge() >= 18;
	}
	
	public List<ClientPhone> validPhoneNumbers(Client client) throws ClientException{
		
		if(client.getPhones() == null) {
			
			client.setPhones(new ArrayList<>());
			
			return client.getPhones();
		}
		
		List<ClientPhone> listPhoneNumbers = client.getPhones();
		for(ClientPhone phoneNumber : listPhoneNumbers) {
			String number = phoneNumber.getPhoneNumber();
			
			if(number == null || !number.startsWith("+52")) {
				
				throw new ClientException("Your phone number must start with +52");
			}
		}
		
		return listPhoneNumbers;
	}
	
	public String validZipCode(Client client) {
		
		String zipCode = client.getZipCode();
		
		if(zipCode.length() == 5) {
			
			return zipCode;
		}
		
		while(zipCode.length() < 5) {
			
			zipCode += "0";
		}
		
		return zipCode;
	}

}
