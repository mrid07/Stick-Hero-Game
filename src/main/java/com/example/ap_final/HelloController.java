package com.example.ap_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView ig,stick_hero;

    @FXML
    private ImageView Imagebg1;

    @FXML
    private Button button;


    public void play(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        System.out.println("Aaa");
        h.changeScene("afterScene.fxml");
    }
/*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}