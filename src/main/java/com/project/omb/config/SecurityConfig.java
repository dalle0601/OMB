package com.project.omb.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
                        )
                )
                // 경로별 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // H2 콘솔, 회원가입 및 로그인 엔드포인트는 인증 없이 접근 가능
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/user/register", "/user/login").permitAll()

                        // 기타 엔드포인트는 인증된 사용자만 접근 가능
                        .anyRequest().authenticated()
                )

                // 로그인 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/login/login") // 커스텀 로그인 페이지 URL
                        .usernameParameter("username") // 로그인 요청에서 사용자 이름 파라미터명
                        .passwordParameter("password") // 로그인 요청에서 비밀번호 파라미터명
                        .loginProcessingUrl("/login/login-proc") // 로그인 처리 URL
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 리다이렉트 URL
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트 URL
                );

        return http.build();
    }
}
