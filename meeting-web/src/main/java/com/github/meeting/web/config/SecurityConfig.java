package com.github.meeting.web.config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.nio.charset.Charset;

//import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
//@EnableWebSecurity
public class SecurityConfig {
//public class SecurityConfig extends WebSecurityConfiguration {




//    @Bean
//    @ConditionalOnMissingBean(value = SecurityWebFilterChain.class)
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//        SubjectDnX509PrincipalExtractor principalExtractor =
//                new SubjectDnX509PrincipalExtractor();
//
//
//        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
//                new SecurityContextServerLogoutHandler(),
//                new WebSessionServerLogoutHandler()
//        );
//
//        principalExtractor.setSubjectDnRegex("OU=(.*?)(?:,|$)");
//
//        ReactiveAuthenticationManager authenticationManager = authentication -> {
//            authentication.setAuthenticated("Trusted Org Unit".equals(authentication.getName()));
//            return Mono.just(authentication);
//        };
//
//        http
//                .x509(x509 -> x509
//                        .principalExtractor(principalExtractor)
//                        .authenticationManager(authenticationManager)
//                )
//                .authorizeExchange(exchange -> {
//                     exchange
//                             .pathMatchers("/", "/error", "/webjars/**", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
//                    .pathMatchers("/api/**").permitAll()
//                    .pathMatchers("/account/**").permitAll()
//                    .pathMatchers("/api/meetings/**").permitAll()
//                    .pathMatchers("/api/meetings/join/**").permitAll()
//                             .anyExchange()
//                             .authenticated()
////                             .and()
//                     ;
//                })
//                .formLogin(formLoginSpec -> formLoginSpec.loginPage("/login"))
//                .logout(logoutSpec -> logoutSpec.logoutHandler(logoutHandler));
//
//
//                return http.build();
//    }
//
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder()
//                        .encode("password"))
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder("PASSWORD", 20, 256,
//                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//    }



}
