package com.github.peng;

import cn.hutool.core.lang.hash.Hash;
import com.github.peng.gui.app.controller.HelloController;
import com.github.peng.gui.util.FxmlLoader;
import com.sun.javafx.fxml.FXMLLoaderHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("/HelloController.fxml"));
        var scene = FxmlLoader.applySingleScene(HelloController.class);
        scene.heightProperty();
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}