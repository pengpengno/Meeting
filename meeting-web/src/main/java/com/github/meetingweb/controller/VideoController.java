package com.github.meetingweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/live")
@Slf4j
public class VideoController {
//    @GetMapping(value = "/live.mp4")
//    @ResponseBody
//    public ResponseEntity<StreamingHttpOutputMessage> livestream(@PathVariable("id") Long tipperId) throws Exception {
//
//        String rtspUrl = "rtsp://user:passwd@192.168.1.200:554/ISAPI/Streaming/channels/101/live";
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(os -> {
//                    FFmpeg.atPath()
//                            .addArgument("-re")
//                            .addArguments("-acodec", "pcm_s16le")
//                            .addArguments("-rtsp_transport", "tcp")
//                            .addArguments("-i", rtspUrl)
//                            .addArguments("-vcodec", "copy")
//                            .addArguments("-af", "asetrate=22050")
//                            .addArguments("-acodec", "aac")
//                            .addArguments("-b:a", "96k" )
//                            .addOutput(PipeOutput.pumpTo(os)
//                                    .disableStream(StreamType.AUDIO)
//                                    .disableStream(StreamType.SUBTITLE)
//                                    .disableStream(StreamType.DATA)
//                                    .setFrameCount(StreamType.VIDEO, 100L)
//                                    //1 frame every 10 seconds
//                                    .setFrameRate(0.1)
//                                    .setDuration(1, TimeUnit.HOURS)
//                                    .setFormat("ismv"))
//                            .addArgument("-nostdin")
//                            .execute();
//                });
//
//    }
}