package com.arunveersingh.rest.webservices.restfulwebservices.versioning;

public class AccountV1 {
	
	private String name;

	public AccountV1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
