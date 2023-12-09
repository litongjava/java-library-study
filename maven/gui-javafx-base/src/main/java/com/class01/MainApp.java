package com.class01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("restriction")
@Slf4j
public class MainApp extends Application {

  Stage window;
  Button button;

  public static void main(String[] args) {
    launch(args); // It calls start method defined bellow...
  }

  @Override
  public void init() throws Exception {
    log.info("init");
    super.init();
  }

  @Override
  public void stop() throws Exception {
    log.info("stop");
    super.stop();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    log.info("start");
    window = primaryStage;
    window.setTitle(" TutorialsFace - JavaFX");
    // Creating a simple UI button
    button = new Button("Click me");
    // StackPane is a type of layout which we will look at later..
    StackPane layout = new StackPane();

    // Adding the button to the layout
    layout.getChildren().add(button);

    // Create scene with resolution of 300x250 and add layout to it...
    Scene scene = new Scene(layout, 300, 250);

    // Add scene to the stage i.e to the window
    window.setScene(scene);
    window.show();
  }
}