package com.grebesche.servicelauncher.ui;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LogUI {

  private VBox logContainer;
  private Label logLabel;

  public LogUI() {
    this.logContainer = new VBox();
    this.logContainer.setStyle("-fx-border-color: #2e8b57;-fx-border-width: 2px;-fx-padding: 10;-fx-spacing: 8;");

    ScrollPane sp = new ScrollPane();
    logLabel = new Label("No log here");
    sp.setContent(logLabel);
    logContainer.getChildren().add(sp);
  }

  public VBox getLogContainer() {
    return logContainer;
  }

  public void appendLog(String log) {
    logLabel.setText(logLabel.getText() + log);
  }

  public void clearLog() {
    logLabel.setText("");
  }
}
