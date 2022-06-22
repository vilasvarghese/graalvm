package com.javatechie.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Customer {

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Customer() {
		super();
	}
	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private int id;
    private String name;
    
    
}
