package com.github.meeting.web.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {


    ConcurrentHashMap<String, WebSocketSession> sessionsMap = new ConcurrentHashMap<>();

    @Override
    public List<String> getSubProtocols() {
        return WebSocketHandler.super.getSubProtocols();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        if (!sessionsMap.containsKey(session.getId())){
            sessionsMap.put(session.getId(), session);
        }

        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(payload -> {
                    log.info("Received: " + payload);
                    sessionsMap.forEach((k, v) -> {
                        if (v.isOpen()){
                            v.send(Mono.just(v.textMessage(payload)));
                        }
                    });

                    return Flux.fromStream(sessionsMap.values().stream())
                            .flatMap(ses -> ses.send(Mono.just(session.textMessage(payload))));

                })
                .then();

    }

    private void sendMsg(WebSocketSession session , String msg) {

    }

}
