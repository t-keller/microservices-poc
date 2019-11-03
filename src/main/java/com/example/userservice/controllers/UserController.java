package com.example.userservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.dto.UserDto;

@RestController
public class UserController {

	@RequestMapping("/users")
	public List<UserDto> users() {
		return Arrays.asList(new UserDto(1, "THOKEL4"));
	}

}
