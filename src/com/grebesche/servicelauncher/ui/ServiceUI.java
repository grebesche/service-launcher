package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.ActionCallback;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ServiceUI {

  private HBox container;
  private Label label;
  private ActionCallback startServiceExecutor;
  private ActionCallback editServiceExecutor;
  private ActionCallback deleteServiceExecutor;

  public ServiceUI() {
    container = new HBox();
    label = new Label();
    container.getChildren().add(label);
    Button startButton = new Button();
    startButton.setText("start");
    startButton.setOnMouseClicked(event -> startServiceExecutor.execute());
    container.getChildren().add(startButton);
    Button editButton = new Button();
    editButton.setText("edit");
    editButton.setOnMouseClicked(event -> editServiceExecutor.execute());
    container.getChildren().add(editButton);
    Button deleteButton = new Button();
    deleteButton.setText("delete");
    deleteButton.setOnMouseClicked(event -> deleteServiceExecutor.execute());
    container.getChildren().add(deleteButton);
  }

  public HBox getContainer() {
    return container;
  }

  public void setStartServiceExecutor(ActionCallback startServiceExecutor) {
    this.startServiceExecutor = startServiceExecutor;
  }

  public void setEditServiceExecutor(ActionCallback editServiceExecutor) {
    this.editServiceExecutor = editServiceExecutor;
  }

  public void setDeleteServiceExecutor(ActionCallback deleteServiceExecutor) {
    this.deleteServiceExecutor = deleteServiceExecutor;
  }

  public void setService(final Service service) {
    label.setText(service.getName());
  }
}
