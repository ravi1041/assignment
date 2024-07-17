package com.artiscien.assignment.service;

public interface JwtService {
	String generateAccessToken(String username);

	String generateRefreshToken(String username);

	String extractUsername(String token);

	boolean validateToken(String token, String username);

}
