package com.grebesche.servicelauncher.ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainUI {

  private BorderPane borderPane;

  public void start(Stage primaryStage) throws Exception {
    borderPane = new BorderPane();

    ServiceListUI serviceListUI = new ServiceListUI(primaryStage);
    borderPane.setLeft(serviceListUI.getServiceListContainer());

    LogUI logUI = new LogUI();
    borderPane.setCenter(logUI.getLogContainer());

    primaryStage.setTitle("Service launcher");
    primaryStage.setScene(new Scene(borderPane, 500, 500));
    primaryStage.show();
  }
}
