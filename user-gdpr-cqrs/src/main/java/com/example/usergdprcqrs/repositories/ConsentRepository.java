package com.example.usergdprcqrs.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.usergdprcqrs.entities.Consent;

public interface ConsentRepository extends PagingAndSortingRepository<Consent, Integer> {

	@Override
	List<Consent> findAll();
}
