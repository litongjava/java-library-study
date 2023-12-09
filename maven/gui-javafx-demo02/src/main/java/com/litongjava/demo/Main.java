
package com.litongjava.demo;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL resource = getClass().getResource("/fxml/sample.fxml");
    if (resource != null) {
      Parent root = FXMLLoader.load(resource);
      primaryStage.setTitle("Hello Java FX");
      primaryStage.setScene(new Scene(root, 600, 600));
      primaryStage.show();
    } else {
      System.out.println("can't find fxml");
      return;
    }

  }

  public static void main(String[] args) {
    launch(args);
  }
}
