package com.github.meeting.web.config;

import com.github.meeting.web.ws.MyWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.thymeleaf.spring6.ISpringWebFluxTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
//@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer  {



    private final ISpringWebFluxTemplateEngine templateEngine;



//    private final  WebSocketHandler webSocketHandler;



    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/path", new MyWebSocketHandler());
        int order = -1; // before annotated controllers

        return new SimpleUrlHandlerMapping(map, order);
    }
}
