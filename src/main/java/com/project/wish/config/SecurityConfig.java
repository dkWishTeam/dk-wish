package com.project.wish.config;

import com.project.wish.filter.CustomAuthenticationFilter;
import com.project.wish.filter.JwtAuthenticationFilter;
import com.project.wish.jwt.JwtTokenProvider;
import com.project.wish.service.AuthenticationProviderService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProvider;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final JwtTokenProvider jwtTokenProvider; // JWT를 처리하는 클래스

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         기존의 UsernamePasswordAuthenticationFilter 를 거치게 되는 경우
         attemptAuthentication 에서는 json 의 값을 받지 못한다.
         따라서 커스텀 필터를 구현해서 UsernamePasswordAuthenticationFilter 의 attemptAuthentication 를
         재정의 해서 json 형태의 requestBody 를 파싱할 수 있게 했다.
         */
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        customAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        // 인증 시에 만 사용되는 필터임을 설정
        customAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());

        http.cors().configurationSource(corsConfigurationSource())
            .and()
            .authorizeRequests()
//            .antMatchers("/", "/login", "/logout", "/place/*","/wishes/*").permitAll() // 로그인과 메인화면에 누구나 접근 가능하게 설정
//            .antMatchers("/users/*").hasAnyRole("USER","ADMIN")
//            .antMatchers("/wishes/*").hasAnyRole("USER","ADMIN")
//            .antMatchers("/admin/*").hasRole("ADMIN")
//            .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
            .anyRequest().permitAll()
            .and()
            .csrf().disable()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), CustomAuthenticationFilter.class)
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .logout()
            .logoutUrl("/logout") // 로그아웃 요청을 처리할 엔드포인트 지정
            .logoutSuccessUrl("/"); // 로그아웃 성공 시 리디렉션 될 URL 지정
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // 여기를 수정
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        // todo 1: Authentication Manager 를 구현해야한다.
        // todo 2: configure(AuthenticationManagerBuilder auth) 를 구현하면 자동으로 AuthenticationManager 가 생긴다.
        // todo 3: 생긴 AuthenticationManager 에 bean 으로 등록해놓은 AuthenticationProvider 를 꽂아줬다.
    }
}
