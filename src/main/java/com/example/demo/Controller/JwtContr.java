package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.Model.JwtRequest;
import com.example.demo.Model.JwtResponse;
import com.example.demo.Service.JwtService;

@RestController
public class JwtContr 
{
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtReq)throws Exception
	{
		System.out.println(jwtReq);
		try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(jwtReq.getUsername(), jwtReq.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			e.printStackTrace();
			throw new Exception("Wrong Username or Password");
		}
		
		UserDetails userDet=this.service.loadUserByUsername(jwtReq.getUsername());
		
		String token=this.jwtUtil.generateToken(userDet);
		System.out.println("JWT"+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
