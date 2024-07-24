package com.artiscien.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artiscien.assignment.bean.AuthenticationRequest;
import com.artiscien.assignment.bean.AuthenticationResponse;
import com.artiscien.assignment.bean.RefreshTokenRequest;
import com.artiscien.assignment.service.JwtService;
import com.artiscien.assignment.service.UserDetailsServiceImpl;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws UsernameNotFoundException {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		if (!"admin".equals(userDetails.getPassword())) {
			throw new UsernameNotFoundException("Wrong password");
		}
		final String accessToken = jwtService.generateAccessToken(userDetails.getUsername());
		final String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		String username = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
		final String accessToken = jwtService.generateAccessToken(username);
		return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshTokenRequest.getRefreshToken()));
	}

}
