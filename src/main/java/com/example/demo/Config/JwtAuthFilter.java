package com.example.demo.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.Jwt.JwtUtil;

@Component
public class JwtAuthFilter extends OncePerRequestFilter 
{
	@Autowired
	private UserDetailsService service;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
				String requestTokenHeader = request.getHeader("Authorization");
				String username=null;
				String jwtToken=null;

				if(requestTokenHeader!= null && requestTokenHeader.startsWith("Bearer "))
				{
					jwtToken=requestTokenHeader.substring(7);

					try{

						username = this.jwtUtil.extractUsername(jwtToken);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					UserDetails userDet=this.service.loadUserByUsername(username);
					
					if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)

					{
						UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDet, null, userDet.getAuthorities());
						upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(upat);
					}
					else

					{

					System.out.println("Token is not validated..");

					}
				}
				 
				filterChain.doFilter(request, response);
	}
	
}
