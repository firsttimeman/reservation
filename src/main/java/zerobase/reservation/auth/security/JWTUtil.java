package zerobase.reservation.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zerobase.reservation.auth.type.MemberType;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final long tokenValidTime;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret,
                   @Value("${spring.jwt.token-valid-time}") long tokenValidTime) {

        // SecretKey를 Keys.hmacShaKeyFor 메소드로 생성
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.tokenValidTime = tokenValidTime;
    }

    // 사용자 이메일 추출
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

    // 사용자 역할 추출
    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles", String.class);
    }

    // 토큰 만료 여부 확인
    public Boolean isExpired(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (JwtException e) {
            throw new JwtException("Invalid Token");
        }
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Claims claims = this.parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰에서 Claims 추출
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // JWT 토큰 생성
    public String createToken(String userEmail, MemberType memberType) {

        if (memberType == null) {
            throw new IllegalArgumentException("MemberType cannot be null");
        }


        Date now = new Date();
        return Jwts.builder()
                .claim("email", userEmail)
                .claim("roles", memberType.name())
                .claim("jti", generateRandomToken())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + tokenValidTime))
                .signWith(secretKey)
                .compact();
    }

    // 고유한 토큰 ID 생성
    private String generateRandomToken() {
        return UUID.randomUUID().toString();
    }
}