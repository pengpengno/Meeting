package com.github.meeting.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.socket.client.WebSocketClient;

//@RestController
@Controller
@RequestMapping()
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String videoConference(Model model) {
        return "video";
    }


    @GetMapping("/view")
    public String view(Model model) {
//        WebSocketClient
        return "view";
    }



    @GetMapping("/meetingroom")
    public String meetingroom(Model model) {
        return "meetingroom";
    }

    @GetMapping("/rtc")
    public String webRtcMeeting(Model model) {
        return "webRtcMeeting";
    }
}