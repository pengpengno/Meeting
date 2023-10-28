package com.github.peng.cv;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.awt.*;

@Slf4j
/***
 * 屏幕录制器
 */
@Singleton
public class ScreenGrabber {


    /***
     * 桌面屏幕录制器
     * @return 返回 FFmpeg 屏幕录制器
     */
    public FrameGrabber desktopScreenCapture () {

        Integer screen = 1;

        FrameGrabber grabber = new FFmpegFrameGrabber("desktop");

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        var screenDevices = ge.getScreenDevices();

        var screenDevice = screenDevices[screen];

        var refreshRate = screenDevice.getDisplayMode().getRefreshRate();

        log.info("refreshRate : {}",refreshRate);

        refreshRate = 10 ;

        try {
            var desktop = FFmpegFrameGrabber.createDefault("desktop");

            var audio = FFmpegFrameGrabber.createDefault("audio=virtual-auodi-capturer");

            audio.setFormat("dshow");

            desktop.setFormat("gdigrab");

            desktop.setFrameRate(refreshRate);

            desktop.setVideoCodec(avcodec.AV_CODEC_ID_H264);

            desktop.setVideoFrameNumber(0);

            desktop.start();
        }catch (Exception e) {

            log.error("创建本地分享异常{}", ExceptionUtil.stacktraceToString(e));

        }




        return grabber;
    }




}
