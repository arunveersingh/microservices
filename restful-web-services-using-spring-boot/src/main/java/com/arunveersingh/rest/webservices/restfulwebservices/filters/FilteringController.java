package com.arunveersingh.rest.webservices.restfulwebservices.filters;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/getBeanWithStaticFilter")
	public BeanWithStaticFilters getBean() {
		return new BeanWithStaticFilters("property1", "property2", "property3");
	}
	
	@GetMapping("/getBeanWithDynamicFilter")
	public MappingJacksonValue getBeanWithDynamicFiletrs() {
		BeanWithDynamicFilters bean = new BeanWithDynamicFilters("property1", "property2", "property3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("property1", "property3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("BeanWithStaticFilters", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);
		
		
		return mapping;
	}

}
