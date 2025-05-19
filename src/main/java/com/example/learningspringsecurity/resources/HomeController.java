package com.example.learningspringsecurity.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
public class HomeController {
	@Value("${spring.application.name}")
	String appName;

	@RequestMapping("/home")
	public String index() {
		System.out.println("roke" + appName);
		return 	"index.html";
	}
}
