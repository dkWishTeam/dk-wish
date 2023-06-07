package com.project.wish.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    // JWT 토큰 유효시간
    private final long TOKEN_VALIDITY = 3600L * 1000L;  // 1시간

    private final UserDetailsService userDetailsService;
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 서버가 두대이상일때 문제 발생

    // 토큰 생성
    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Claims claims = Jwts.claims().setSubject(username);  // JWT payload 에 저장되는 정보단위
        // jwt 만들어질 때 claims 확인
        claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));  // 정보는 key / value 쌍으로 저장됩니다. 이 경우에는 사용자 권한.

        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_VALIDITY);

        return Jwts.builder()
            .setClaims(claims)  // claim 정보 설정
            .setIssuedAt(now)  // 토큰 발행 시간 정보
            .setExpiration(validity)  // set Expire Time
            .signWith(key, SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과 secret 값 세팅
            .compact();
    }

    // 토큰 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Expired or invalid JWT token");
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }
}

