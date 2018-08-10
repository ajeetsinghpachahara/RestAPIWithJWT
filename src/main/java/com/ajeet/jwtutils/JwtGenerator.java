package com.ajeet.jwtutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ajeet.jwtmodel.JwtUser;

@Component
public class JwtGenerator {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expires_in}")
	private int expireIn;

    public String generate(JwtUser jwtUser) {

    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.SECOND, expireIn);
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());
        claims.setExpiration(cal.getTime());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
