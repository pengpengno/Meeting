package com.github.peng;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.hash.Hash;
import cn.hutool.extra.spring.SpringUtil;
import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ClientToolkit;
import com.github.peng.connect.connection.client.tcp.reactive.ReactorTcpClient;
import com.github.peng.connect.model.proto.Account;
import com.github.peng.gui.app.controller.HelloController;
import com.github.peng.gui.util.FxmlLoader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.javafx.fxml.FXMLLoaderHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Scene scene = FxmlLoader.applySingleScene(HelloController.class);

        ClientLifeStyle connect =
                ReactorTcpClient.getInstance().config(new InetSocketAddress("localhost", 8080))
                .connect();

//        ClientToolkit.reactiveClientAction().sendString("connection established").subscribe();

        ClientToolkit.reactiveClientAction()
                .sendMessage(Account.AccountInfo.newBuilder().setEMail("pen").build()).subscribe();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}