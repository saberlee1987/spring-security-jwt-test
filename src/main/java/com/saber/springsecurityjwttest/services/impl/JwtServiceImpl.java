package com.saber.springsecurityjwttest.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.saber.springsecurityjwttest.services.JwtService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JwtServiceImpl implements JwtService {

    @Value(value = "${jwt.jwtExpirationInMilliSecond}")
    private int jwtExpirationInMilliSecond;
    @Value(value = "${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles =
                userDetails.getAuthorities();
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMilliSecond))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            JWT.decode(token);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid Credentials", ex);
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<Map<String, String>> list = (List<Map<String, String>>) claims.get("roles");
        for (Map<String, String> map : list) {
            map.keySet().forEach(key -> {
                roles.add(new SimpleGrantedAuthority(map.get(key)));
            });
        }
        return roles;
    }

}
