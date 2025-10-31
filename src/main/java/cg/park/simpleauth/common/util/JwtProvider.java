package cg.park.simpleauth.common.util;

import cg.park.simpleauth.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(@Value("${jwt.key}") String secret) {
        if (secret == null || secret.getBytes().length < 32) {
            throw new IllegalArgumentException("Secret must be at least 32 bytes for HS256");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String create(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis()); // 만료기간 1일

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setIssuer("PCG")
            .setSubject(user.getUserId())
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256) // ✅ Key 객체 필요
            .compact();
    }

    public Claims parseJwtToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)  // Key 객체로 서명 검증
            .build()
            .parseClaimsJws(BearerRemove(token))  // JWT 검증
            .getBody();
    }

    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }

}
