package com.saber.springsecurityjwttest.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {

    String generateToken(UserDetails userDetails);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    List<SimpleGrantedAuthority> getRolesFromToken(String token);
}
