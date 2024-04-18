package com.github.meeting.demo.pattern.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final ApplicationEventPublisher eventPublisher;

    public void registerUser(String username) {
        // 注册新用户逻辑
        // ...

        // 发布用户注册事件
        eventPublisher.publishEvent(new UserRegisteredEvent(this, username));
    }
}