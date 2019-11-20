package com.example.gdprservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.gdprservice.entities.Treatment;

public interface TreatmentRepository extends PagingAndSortingRepository<Treatment, Integer> {

}
