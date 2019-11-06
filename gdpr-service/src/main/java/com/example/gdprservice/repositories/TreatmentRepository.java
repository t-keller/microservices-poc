package com.example.gdprservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.gdprservice.entities.Treatment;

@RepositoryRestResource(collectionResourceRel = "treatments", path = "treatments")
public interface TreatmentRepository extends PagingAndSortingRepository<Treatment, Integer> {

}
