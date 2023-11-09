package com.github.bytecpp;

import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ClientToolkit;
import com.github.peng.connect.connection.client.ReactiveClientAction;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspVersions;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;

public class CameraScreen {
    public static void main(String[] args) throws Exception {

        ClientLifeStyle connect = ClientToolkit.clientLifeStyle()
                .connect(new InetSocketAddress("localhost", 8080));

        CamareScreenCapture camareScreenCapture = new CamareScreenCapture();

        ReactiveClientAction reactiveClientAction = ClientToolkit.reactiveClientAction();


        // Create a frame grabber for your camera (you can also load a video file)
        FrameGrabber grabber = new OpenCVFrameGrabber(0);
//
//        FrameGrabber grabber = camareScreenCapture
//                .createGrabber(CamareScreenCapture.CameraType.DESKTOP);
        grabber.setFormat("gdigrab");
//        linux use this
        OpenCVFrameRecorder recorder = new OpenCVFrameRecorder("desktop.mp4", 1920, 1080);
        grabber.setFormat("x11grab");
        grabber.setFrameRate(28);
//        FrameGrabber grabber = new FFmpegFrameGrabber("desktop");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

//        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(bos, grabber.getImageWidth(), grabber.getImageHeight());

//        recorder.setFormat("avi");
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.start();
        new Thread(()-> {
            while (true){
//                recorder.get
                if (bos.size() > 0){
                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
                    buffer.writeBytes(bos.toByteArray());

                    DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0,
                            RtspMethods.OPTIONS, "/live", buffer);

                    reactiveClientAction.sendObject(defaultFullHttpRequest).subscribe();
                    bos.reset();
                }

            }
        }).start();

        try {
            // Start the grabber
            grabber.start();

            // Create a canvas frame for displaying the video
            CanvasFrame canvasFrame = new CanvasFrame("Video Processing");

            canvasFrame.setCanvasSize(800, 600);

            // Create a frame converter
            OpenCVFrameConverter.ToMat frameConverter = new OpenCVFrameConverter.ToMat();

            while (true) {
                Frame frame = grabber.grab();
                if (frame == null) {
                    break;
                }

                // Display the processed frame
//                canvasFrame.showImage(frameConverter.convert(frame));
                canvasFrame.showImage(frame);
                recorder.record(frame);
                recorder.flush();

                // Exit the application when the user closes the window
                if (canvasFrame.getCanvas().isShowing()) {
                    canvasFrame.setDefaultCloseOperation(CanvasFrame.DO_NOTHING_ON_CLOSE);
                } else {
                    break;
                }
            }

            // Release resources
            grabber.stop();
            recorder.stop();
            canvasFrame.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}