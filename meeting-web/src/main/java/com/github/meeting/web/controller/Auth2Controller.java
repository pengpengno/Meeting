package com.github.meeting.web.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;

/**
 * Description:
 * <p>
 * </p>
 *
 * @author pengpeng
 * @version 1.0
 * @since 2024/5/10
 */

@RestController
//@RequestMapping("/auth2")
public class Auth2Controller {



    @GetMapping("/login/oauth2/code/github")
    public Mono<Void> githubLoginSuccess(ServerHttpRequest request, ServerHttpResponse response) {
        return Mono.fromRunnable(() -> {
            response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
            response.getHeaders().setLocation(URI.create("/"));
        });
    }


    @GetMapping("oauth")
    public Mono<String> oauthPage() {

        return Mono.just("oauth") ;
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("Ov23litHvfyRrBkL6qQp")
                .clientSecret("7c4129a7851258a2d77e3ffb80229e87d8c7dde0")
                .redirectUri("http://baidu.com")
                .build());
    }


}