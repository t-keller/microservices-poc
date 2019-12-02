package com.example.usergdprcqrs.dto;

import com.example.usergdprcqrs.entities.Treatment;

import lombok.Data;

@Data
public class ConsentDto {
	private int id;
	private String personId;
	private Treatment treatment;
	private boolean optin;
}
