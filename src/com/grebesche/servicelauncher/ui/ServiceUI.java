package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.EditServiceCallback;
import com.grebesche.servicelauncher.actions.StartServiceExecutor;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ServiceUI {

  private HBox container;
  private Label label;
  private Button startButton;
  private Button editButton;
  private StartServiceExecutor startServiceExecutor;
  private EditServiceCallback editServiceExecutor;

  public ServiceUI() {
    container = new HBox();
    label = new Label();
    container.getChildren().add(label);
    startButton = new Button();
    startButton.setText("start");
    container.getChildren().add(startButton);
    editButton = new Button();
    editButton.setText("edit");
    container.getChildren().add(editButton);
    startButton.setOnMouseClicked(event -> startServiceExecutor.start());
    editButton.setOnMouseClicked(event -> editServiceExecutor.edit());
  }

  public HBox getContainer() {
    return container;
  }

  public void setStartServiceExecutor(StartServiceExecutor startServiceExecutor) {
    this.startServiceExecutor = startServiceExecutor;
  }

  public void setEditServiceExecutor(EditServiceCallback editServiceExecutor) {
    this.editServiceExecutor = editServiceExecutor;
  }

  public void setService(final Service service) {
    label.setText(service.getName());

  }
}
