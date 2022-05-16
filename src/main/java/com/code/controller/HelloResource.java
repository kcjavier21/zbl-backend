package com.code.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HelloResource {
	
	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World!";
	}

}
