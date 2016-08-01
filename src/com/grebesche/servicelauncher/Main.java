package com.grebesche.servicelauncher;

import com.grebesche.servicelauncher.ui.MainUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  private MainUI mainUI;

  @Override
  public void start(Stage primaryStage) throws Exception {
    mainUI = new MainUI();
    mainUI.start(primaryStage);
  }


  public static void main(String[] args) {
    launch(args);
  }
}
