package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RequestHandler {
	
	@RequestMapping("/test")
	public String test(@RequestParam String name)
	{
		return name;
	}
}
