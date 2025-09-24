package com.carlosgalvan.client.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosgalvan.client.web.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	

}
