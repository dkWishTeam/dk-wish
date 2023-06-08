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
    // todo 왜 여기에 비밀번호가 "" 인 token 을 만드는가
    //UsernamePasswordAuthenticationToken 객체를 생성할 때, 두 번째 매개변수로 전달하는 "credentials"는 일반적으로 사용자가 제공한 비밀번호입니다.
    // 그러나 JWT를 사용하는 경우, 이미 사용자는 자신의 사용자 이름과 비밀번호를 제공하고, 이를 통해 JWT를 얻었습니다.
    //JWT 자체가 사용자의 인증 정보를 포함하므로, 이후 JWT를 사용하여 인증을 수행하는 과정에서는 원래의 비밀번호를 다시 사용할 필요가 없습니다.
    // 따라서 여기에서 ""를 사용하여 비밀번호 필드를 비워두는 것입니다.

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }
}

