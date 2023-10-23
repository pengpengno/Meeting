package com.github.peng.gui.controller;


import com.github.peng.gui.viewMain.MainView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class MainController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private FlowPane dashBoardFlowPane;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Button minButton;

    @FXML
    private Button maxButton;

    @FXML
    private Pane  viewMainPane;

    MainView mainView;

    DashBoardPane dashBoard;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Main Controller is init");
        Assert.notNull(AccountContext.getCurAccount(),"Current Account not be null!");
        mainView.initialize(null,null);
        dashBoard.initialize(null,null);


        mainView.prefWidthProperty().bind(viewMainPane.widthProperty());
        mainView.prefHeightProperty().bind(viewMainPane.heightProperty());
        dashBoard.prefHeightProperty().bind(dashBoardFlowPane.heightProperty());
        dashBoard.prefWidthProperty().bind(dashBoardFlowPane.widthProperty());

        viewMainPane.getChildren().add(mainView);
        dashBoardFlowPane.getChildren().add(dashBoard);


    }



    protected void initSearch(){
        searchField.textProperty().addListener((obs-> {
            String text = searchField.getText();
            AccountSearchVo build =
                AccountSearchVo.builder()
                .likeAccount(searchField.getText())
                .build();
        }));
    }

    @FXML
    void searchAcc(InputMethodEvent event) {
        String text = searchField.getText();
        log.info("当前文本为 {} ",text);
        AccountSearchVo build =
                AccountSearchVo.builder()
                .likeAccount(searchField.getText())
                .build();
    }

    public static void show(){
        Stage stage = FxmlLoader.applySinStage("com\\ifx\\client\\app\\fxml\\main.fxml");
        log.info("prepare to show  main");
        Image icon = new Image(FileUtil.getInputStream("icon/title/conversation.png"));
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("IFx");
        stage.show();

    }


}
