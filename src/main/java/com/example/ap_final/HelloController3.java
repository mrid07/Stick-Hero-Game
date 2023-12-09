package com.example.ap_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController3 {
    @FXML
    private AnchorPane gamepane;
    @FXML
    Button btn;
    public void retry(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changeScene("afterscene.fxml");
    }

    public void exit_back(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changeScene("hello-view.fxml");
    }
}
