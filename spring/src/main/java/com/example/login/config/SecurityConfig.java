package com.example.login.config;

import com.example.login.jwt.JwtAccessDeniedHandler;
import com.example.login.jwt.JwtAuthenticationEntryPoint;
import com.example.login.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration // IoC 빈(bean)을 등록
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .httpBasic().disable()  // https만 사용
                .csrf().disable()   // 토큰 사용위해 Cross Site Request Forgery off?
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session off

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)  // jwt 진입점
                .accessDeniedHandler(jwtAccessDeniedHandler)    // jwt 예외처리?

                .and()
                .authorizeRequests()
                .antMatchers("/api-docs").permitAll()
                .antMatchers("/swagger-ui/index.html").permitAll()
                .antMatchers("/api/member/signup").permitAll()
                .antMatchers("/api/member/signin").permitAll()
                .anyRequest().permitAll()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()

//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));



        ;

        return http.build();
    }
}