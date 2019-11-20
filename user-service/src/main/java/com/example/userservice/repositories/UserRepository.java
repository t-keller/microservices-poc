package com.example.userservice.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.userservice.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@Override
	List<User> findAll();
}
