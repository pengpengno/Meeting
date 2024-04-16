package com.github.meeting.web.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    private Map<String, String> meetingParticipants = new HashMap<>();

    @PostMapping
    public ResponseEntity<Map<String, String>> createMeeting(@RequestBody Map<String, String> requestBody) {
        String topic = requestBody.get("topic");
        String meetingId = generateMeetingId(); // 自定义生成会议ID的方法
        meetingParticipants.put(meetingId, ""); // 将会议ID与空的参与者列表关联起来
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", meetingId);
        return ResponseEntity.ok(responseData);
    }
    public static String generateMeetingId() {
        // 使用UUID来生成唯一的会议ID
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
    }

    @PostMapping("/{meetingId}/join")
    public ResponseEntity<Void> joinMeeting(@PathVariable String meetingId, @RequestParam String participantId) {
        if (!meetingParticipants.containsKey(meetingId)) {
            return ResponseEntity.notFound().build(); // 如果会议ID不存在，返回404
        }
        // 在此处可以根据会议ID将参与者添加到相应的会议中，这里简化为存储在map中
        meetingParticipants.put(meetingId, participantId);
        return ResponseEntity.ok().build();
    }

    // 其他相关的接口，如存储视频流、获取视频流等


    @PostMapping("/offer/{meetingId}")
    public ResponseEntity<Map<String, String>> sendOffer(@PathVariable String meetingId, @RequestBody Map<String, Object> requestBody) {
        // 在这里你需要处理客户端发送的offer，然后生成并发送一个answer给客户端
        String sdp = requestBody.get("sdp").toString();
        // 这里简化为直接返回一个固定的answer，实际情况下需要根据SDP内容生成answer
        String answerSdp = "dummyAnswerSDP";
        Map<String, String> responseData = new HashMap<>();
        responseData.put("answer", answerSdp);
        return ResponseEntity.ok(responseData);
    }
}