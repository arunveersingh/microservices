package com.arunveersingh.rest.webservices.restfulwebservices.versioning;

public class AccountV2 {
	private Name name;

	public AccountV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	

}
