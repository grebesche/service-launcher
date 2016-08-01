package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.DeleteServiceCallback;
import com.grebesche.servicelauncher.actions.EditServiceCallback;
import com.grebesche.servicelauncher.actions.StartServiceExecutor;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ServiceUI {

  private HBox container;
  private Label label;
  private StartServiceExecutor startServiceExecutor;
  private EditServiceCallback editServiceExecutor;
  private DeleteServiceCallback deleteServiceExecutor;

  public ServiceUI() {
    container = new HBox();
    label = new Label();
    container.getChildren().add(label);
    Button startButton = new Button();
    startButton.setText("start");
    startButton.setOnMouseClicked(event -> startServiceExecutor.start());
    container.getChildren().add(startButton);
    Button editButton = new Button();
    editButton.setText("edit");
    editButton.setOnMouseClicked(event -> editServiceExecutor.edit());
    container.getChildren().add(editButton);
    Button deleteButton = new Button();
    deleteButton.setText("delete");
    deleteButton.setOnMouseClicked(event -> deleteServiceExecutor.delete());
    container.getChildren().add(deleteButton);
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

  public void setDeleteServiceExecutor(DeleteServiceCallback deleteServiceExecutor) {
    this.deleteServiceExecutor = deleteServiceExecutor;
  }

  public void setService(final Service service) {
    label.setText(service.getName());
  }
}
