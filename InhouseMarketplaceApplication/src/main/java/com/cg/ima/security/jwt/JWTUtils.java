package com.cg.ima.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtils {

	@Value("${cg.app.jwtSecret}")
	private String jwtSecret;

	@Value("${cg.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT signature: {}" + e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token: {}" + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("JWT token is expired: {}" + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT token is unsupported: {}" + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty: {}" + e.getMessage());
		}

		return false;
	}
}
