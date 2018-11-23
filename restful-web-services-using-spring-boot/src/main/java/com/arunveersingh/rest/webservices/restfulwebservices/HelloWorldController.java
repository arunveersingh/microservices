package com.arunveersingh.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//	@RequestMapping(method=RequestMethod.GET, path = "/hello-world")
	@RequestMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@RequestMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}
	
	@RequestMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("Hello " + name);
	}

}
