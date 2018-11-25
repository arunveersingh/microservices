package com.arunveersingh.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountVersionController {
	
	@GetMapping("v1/getAccount")
	public AccountV1 accountV1() {
		return new AccountV1("arunveer singh");
	}
	
	@GetMapping("v2/getAccount")
	public AccountV2 accountV2() {
		return new AccountV2(new Name("arunveer", "singh"));
	}
	
	@GetMapping(value = "/person/param", params="v1")
	public AccountV1 paramV1() {
		return new AccountV1("arunveer singh");
	}
	
	@GetMapping(value = "/person/param", params="v2")
	public AccountV2 paramV2() {
		return new AccountV2(new Name("arunveer", "singh"));
	}

	@GetMapping(value = "/person/header", headers="X-API-VERSION=1")
	public AccountV1 headerV1() {
		return new AccountV1("arunveer singh");
	}
	
	@GetMapping(value = "/person/header", headers="X-API-VERSION=2")
	public AccountV2 headerV2() {
		return new AccountV2(new Name("arunveer", "singh"));
	}
	
	@GetMapping(value = "/person/produces", produces="application/com.arunveer.app-v1+json")
	public AccountV1 producesV1() {
		return new AccountV1("arunveer singh");
	}
	
	@GetMapping(value = "/person/produces", produces="application/com.arunveer.app-v2+json")
	public AccountV2 producesV2() {
		return new AccountV2(new Name("arunveer", "singh"));
	}
	
	
}
