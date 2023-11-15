package com.github.bytecpp;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ClientToolkit;
import com.github.peng.connect.connection.client.ReactiveClientAction;
import com.github.peng.cv.ScreenShare;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.JavaFXFrameConverter;

import java.net.InetSocketAddress;

@Slf4j
public class DesktopScreenApplicationRtspClient extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Boolean isStop = Boolean.TRUE;

//        ScreenShare screenShare = new ScreenShare();

//        ReactiveClientAction reactiveClientAction = ClientToolkit.reactiveClientAction();

        stage.setTitle("desktop screen");

        ImageView imageVideo = new ImageView();// 用于软件录制显示

        VBox box = new VBox();

        box.getChildren().addAll( imageVideo);

        stage.setScene(new Scene(box));

        stage.show();
        stage.setOnCloseRequest(event -> {// 退出时停止
//                isStop = true;
            System.exit(0);
        });

        String flowUrl1 = "rtsp://127.0.0.1:8080/desktop/group";
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(flowUrl1);
//        fFmpegFrameGrabber.setFormat("rtsp");
        initGrabber(fFmpegFrameGrabber);
        try {

//            screenShare.startGrabber();
//            screenShare.recorderStart();

//            screenShare.configSendConsumer((defaultFullHttpRequest) ->
//                    reactiveClientAction.sendObject(defaultFullHttpRequest).subscribe());

            var javaFXFrameConverter = new JavaFXFrameConverter();

            new Thread(()->{
                try {
                    Frame frame = null;

                    while ( (frame = fFmpegFrameGrabber.grab()) != null){

                        imageVideo.setImage(javaFXFrameConverter.convert(frame));

//                        screenShare.recorder(frame);

                    }
//                    screenShare.release();
                }
                catch (Exception ex){
                    log.error("{}",ex);
                }

            }).start();


        }catch (Exception exception){

            log.error("exception \n{}", ExceptionUtil.stacktraceToString(exception));

        }
    }
    private static boolean initGrabber(FFmpegFrameGrabber grabber) {
        grabber.setOption("stimeout", "15000000");
        grabber.setOption("threads", "1");
        grabber.setOption("buffer_size", "1024000");
        grabber.setOption("rw_timeout", "5000000");
        grabber.setOption("probesize", "5000000");
        grabber.setOption("analyzeduration", "5000000");
        grabber.setOption("rtsp_transport", "tcp");
        grabber.setOption("rtsp_flags", "prefer_tcp");
        try {
            grabber.start(true);
            AVFormatContext avFormatContext = grabber.getFormatContext();
            int streamNum = avFormatContext.nb_streams();
            if (streamNum < 1) {
                return false;
            }
            return true;
        } catch (FrameGrabber.Exception e) {
        }
        return false;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
