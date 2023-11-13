package com.github.peng;

import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ClientToolkit;
import com.github.peng.connect.connection.client.tcp.ReactorTcpClient;
import com.github.peng.connect.model.proto.Account;
import com.github.peng.gui.app.controller.HelloController;
import com.github.peng.gui.util.FxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Scene scene = FxmlLoader.applySingleScene(HelloController.class);

        log.debug("connect status  "  );

        ClientLifeStyle connect =
                ReactorTcpClient.getInstance().config(new InetSocketAddress("localhost", 8080))
                .connect();

        log.debug("connect status {}", connect.isAlive()   );

        ClientToolkit.reactiveClientAction().sendString("connection established").subscribe();

        ClientToolkit.reactiveClientAction()
                .sendMessage(Account.AccountInfo.newBuilder().setEMail("pen").build()).subscribe();

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}