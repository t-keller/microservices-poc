package com.example.usergdprcqrs.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.usergdprcqrs.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@Override
	List<User> findAll();
}
