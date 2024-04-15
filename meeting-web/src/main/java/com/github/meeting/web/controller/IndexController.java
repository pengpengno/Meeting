package com.github.meeting.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping("/video")
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String videoConference(Model model) {
        return "video";
    }


    @GetMapping("/view")
    public String view(Model model) {
        return "view";
    }
}