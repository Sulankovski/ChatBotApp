package com.chatbotapp.chatbotapp.config;

import com.chatbotapp.chatbotapp.exceptions.UnauthorizedAccessException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import io.jsonwebtoken.Claims;

@Service
@AllArgsConstructor
public class JwtService {
    private final String hash = "hu55nfa3pRrBRV4DIlvKVDMrg7jnqla9";
    public final SecretKey secretKey =
            Keys.hmacShaKeyFor(
                    hash.getBytes()
                    );

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    public Optional<String> extractEmailFormToken(String token) {
        return Optional.of(extractClaim(token, Claims::getSubject));
    }

    private <T> T  extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims allClaims = extractAllClaims(token);
        return claimResolver.apply(allClaims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        String email = extractEmailFormToken(jwtToken).orElseThrow(UnauthorizedAccessException::new);
        return userDetails.getUsername().equals(email) && !isTokenExpired(jwtToken);

    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
