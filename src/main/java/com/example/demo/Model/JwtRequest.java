package com.example.demo.Model;

public class JwtRequest 
{
	String Username;
	String Password;
	
	
	public JwtRequest() {
		super();
	}
	public JwtRequest(String username, String password) {
		super();
		Username = username;
		Password = password;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "JwtRequest [Username=" + Username + ", Password=" + Password + "]";
	}
	
}
