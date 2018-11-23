package com.arunveersingh.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
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
	
	@GetMapping("/hello-world-i18/{name}")
	public HelloWorldBean helloWorldI18(@PathVariable String name, @RequestHeader(name="Accept-Language", required=false) Locale locale) {
		
		messageSource.getMessage("hello.world.message", null, locale);
		return new HelloWorldBean("Hello " + name);
	}

}
