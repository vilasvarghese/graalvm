package com.example.graalspring.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table (name = "customer")
class Customer {
    public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
    public Customer() {
	}

    
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    
    /*public String toString(){
    	  return "name"+ name;  
    }*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}