package com.github.meeting.web;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGithubRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@Slf4j
@EnableWebFlux
@EnableR2dbcRepositories
//@EnableJpaRepositories("com.github")
@EntityScan("com.github")
public class Application {

    @SneakyThrows
    public static void main(String[] args) {



        SpringApplication.run(Application.class);

    }



    @Component
    public static class ServerListener implements ApplicationListener<WebServerInitializedEvent> {

        @Override
        public void onApplicationEvent(WebServerInitializedEvent event) {
            int port = event.getWebServer().getPort();
            String serverAddress = "http://localhost:" + port;
            log.info("Server is running at: " + serverAddress);

        }
    }

}
