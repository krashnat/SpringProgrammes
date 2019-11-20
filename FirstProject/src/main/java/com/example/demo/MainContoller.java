package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContoller {

	@RequestMapping("/home")
	public String show() {
		System.out.println("in home");
		return "Home.jsp";
	}

}
