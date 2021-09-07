package com.saber.springsecurityjwttest.controllers;

import com.saber.springsecurityjwttest.dto.AuthenticationDto;
import com.saber.springsecurityjwttest.dto.TokenResponse;
import com.saber.springsecurityjwttest.services.JwtService;
import com.saber.springsecurityjwttest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/generateToken",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponse> generateToken(@RequestBody @Valid AuthenticationDto authenticationDto){

        UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),authenticationDto.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetails userDetails = userService.loadUserByUsername(authenticationDto.getUsername());
        String token =jwtService.generateToken(userDetails);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseEntity.ok(tokenResponse);
    }
}
