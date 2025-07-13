package com.project.Farmer.Support.System.Utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;


@Component
public class JWTUtil {
    private static final String  SECRET_KEY="PRASAD_DURGA_MY_SECRET_1234567890";
    private static final Key key=Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

public String generateToken(String userName,long expiryMinutes){
    return Jwts.builder()
            .setSubject(userName)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiryMinutes*60*1000))
            .signWith(key,SignatureAlgorithm.HS256)
            .compact();
}
}
