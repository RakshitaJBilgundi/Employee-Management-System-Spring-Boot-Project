package com.example.oragnization.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private SecretKey getSigningKey() {
		
	  byte[] keyBytes =Decoders.BASE64.decode(secretKey);
	  return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String username ) {
		Map<String,Object> claims=new HashMap<>();
		Date now =new Date();
		System.out.println("issued at"+now);
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+jwtExpiration))
				.and()
				.signWith(getSigningKey())
				.compact();
	}
	public String extractUsername(String token) {
		 return extractAllClaims(token).getSubject();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username=extractUsername(token);
		 return username.equals(  userDetails.getUsername() )&& !isTokenExpired(token);
	}
	private boolean isTokenExpired(String token) {
		
		return extractAllClaims(token).getExpiration()
				.before(new Date());
	}

	private Claims extractAllClaims(String token) {
		// 
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}
}
