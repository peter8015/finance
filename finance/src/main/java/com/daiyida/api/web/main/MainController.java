package com.daiyida.api.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController {

	


//	@RequestMapping(value = "/login")
//	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
//	
//		return "/login";
//	}
//
//	
//	@RequestMapping(value = "/")
//	public String credit(Model model, HttpServletRequest request) {
//
//		return "/login";
//
//	}
	@RequestMapping(value = "/controller")
	public String controller(Model model, HttpServletRequest request, HttpServletResponse response) {
	
		return "/";
	}
}
