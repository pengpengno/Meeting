module com.github.peng.meetinggui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires meeting.common;

    opens com.github.peng.meetinggui to javafx.fxml;
    exports com.github.peng.meetinggui;
    exports com.github.peng.gui.controller;
    opens com.github.peng.gui.controller to javafx.fxml;
    exports com.github.peng;
    opens com.github.peng to javafx.fxml;
}