package com.example.gdprservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.gdprservice.entities.Consent;

@RepositoryRestResource(collectionResourceRel = "consents", path = "consents")
public interface ConsentRepository extends PagingAndSortingRepository<Consent, Integer> {

}
