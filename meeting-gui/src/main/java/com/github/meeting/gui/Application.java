package com.github.meeting.gui;

import com.github.meeting.common.connect.connection.client.ClientLifeStyle;
import com.github.meeting.common.connect.connection.client.ClientToolkit;
import com.github.meeting.common.connect.connection.client.tcp.ReactorTcpClient;
import com.github.meeting.common.connect.model.proto.Account;
import com.github.meeting.gui.app.controller.HelloController;
import com.github.meeting.gui.app.controller.LoginController;
import com.github.meeting.gui.app.controller.MainController;
import com.github.meeting.gui.app.controller.VideoController;
import com.github.meeting.gui.util.FxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;

/***
 * {@link Launcher use this start up application}
 */
@Slf4j
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = FxmlLoader.applySingleScene(VideoController.class);

        log.debug("connect status  "  );

        try{
            ClientLifeStyle connect =
                    ReactorTcpClient.getInstance().config(new InetSocketAddress("localhost", 8080))
                            .connect();
            log.debug("connect status {}", connect.isAlive());

        }catch (Exception ex){
            log.error("1");
        }


//        ClientToolkit.reactiveClientAction().sendString("connection established").subscribe();



        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}