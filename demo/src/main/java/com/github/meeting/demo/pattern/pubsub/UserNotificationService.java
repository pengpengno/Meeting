package com.github.meeting.demo.pattern.pubsub;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService {

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        // 处理用户注册事件，例如发送通知
        String username = event.getUsername();
        System.out.println("Notification sent for user: " + username);
    }
}