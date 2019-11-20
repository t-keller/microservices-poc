package com.example.gdprservice.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.gdprservice.entities.Consent;

public interface ConsentRepository extends PagingAndSortingRepository<Consent, Integer> {

	@Override
	List<Consent> findAll();
}
