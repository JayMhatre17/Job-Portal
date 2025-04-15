package com.jobportal.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    // Secret key for signing the JWT (use a strong key)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long jwtExpiration = 3600000;


    // Generate a JWT token
 // Generate a JWT token with all user details (excluding password)
    public String generateToken(UserDetails userDetails) {
        if (!(userDetails instanceof CustomUserDetails)) {
            throw new IllegalArgumentException("UserDetails object is not of type CustomUserDetails");
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;

        return Jwts.builder()
                .claim("id", customUserDetails.getId())
                .claim("username", customUserDetails.getUsername())
                .claim("name", customUserDetails.getName())
                .claim("accountType", customUserDetails.getAccountType().toString())
                .claim("profileId", customUserDetails.getProfileId())
                .setSubject(customUserDetails.getUsername()) // Keep the username as the subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // Retrieve email from JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract a specific claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Validate token
    public boolean isTokenValid(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            System.out.println("Token validation error: " + e.getMessage());
            return false;
        }
    }


    // Check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract all claims from JWT token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Generate the signing key using the secret key
    private Key getSigningKey() {
        return secretKey;
    }}
