package com.github.meeting.web;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@Slf4j
//@EnableWebFlux
public class Application {

    @SneakyThrows
    public static void main(String[] args) {

        SpringApplication.run(Application.class);

    }

}
