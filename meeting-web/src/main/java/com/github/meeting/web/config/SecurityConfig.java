package com.github.meeting.web.config;
import com.github.meeting.web.repository.AccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.authentication.preauth.x509.SubjectDnX509PrincipalExtractor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import reactor.core.publisher.Mono;

@Configuration()
//@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
public class SecurityConfig {


//
//    @Bean
//    public ReactiveUserDetailsService userDetailsService(AccountRepository accountRepository) {
//        return username -> accountRepository.findByUsername(username)
//                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with username: " + username)));
//    }
    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder());

//        var localeContext = LocaleContextHolder.getLocaleContext();
        return authenticationManager;
    }

    @Bean
    public ReloadableResourceBundleMessageSource i18nResource() {
        var reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:org/springframework/security/message_ZH.properties");
        return reloadableResourceBundleMessageSource;
    }

    @Bean
    @ConditionalOnMissingBean(value = SecurityWebFilterChain.class)
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        SubjectDnX509PrincipalExtractor principalExtractor =
                new SubjectDnX509PrincipalExtractor();


        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
                new SecurityContextServerLogoutHandler(),
                new WebSessionServerLogoutHandler()
        );

        principalExtractor.setSubjectDnRegex("OU=(.*?)(?:,|$)");

        ReactiveAuthenticationManager authenticationManager = authentication -> {
            authentication.setAuthenticated("Trusted Org Unit".equals(authentication.getName()));
            return Mono.just(authentication);
        };

        http
                .x509(x509 -> x509
                        .principalExtractor(principalExtractor)
                        .authenticationManager(authenticationManager)
                )
                .authorizeExchange(exchange -> {
                     exchange
                     .pathMatchers("/", "/error", "/webjars/**", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
                    .pathMatchers("/auth2/**").permitAll()
                    .pathMatchers("/login/**").permitAll()
                    .pathMatchers("/account/**").permitAll()
//                    .pathMatchers("/api/meetings/**").permitAll()
//                    .pathMatchers("/api/meetings/join/**").permitAll()
                     .anyExchange()
                     .authenticated()
//                             .and()
                     ;
                })
                .oauth2Login(Customizer.withDefaults())
//                .oauth2Login(oAuth2LoginSpec -> oAuth2LoginSpec.)
                .formLogin(formLoginSpec -> formLoginSpec.loginPage("/login"))
                .logout(logoutSpec -> logoutSpec.logoutHandler(logoutHandler));


                return http.build();
    }
//

//    @Bean
//    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryReactiveClientRegistrationRepository(this.githubRegister());
//    }


//    @Bean
//    public ClientRegistration githubRegister() {
//        return CommonOAuth2Provider.GITHUB
//                .getBuilder("github")
//                .clientId("github-client-id")
//                .clientSecret("github-client-secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("{baseUrl}/login/oauth2/code/github}")
//                .scope("openid", "profile", "email", "address", "phone")
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("Google")
//                .build();
//
//    }



    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder()
                        .encode("password"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder("PASSWORD", 20, 256,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
    }



}
