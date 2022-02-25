package com.example.demo.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation. RestController;

@RestController
public class Contr 
{
	@GetMapping("/Result")
	public String getResult()
	{
		String text = "Page is unlocked";
		return text;
	}
}
