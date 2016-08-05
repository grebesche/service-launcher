package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.model.ApplicationModel;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServiceListUI {

  private ApplicationModel applicationModel;
  private ServiceEditorUI serviceEditorUI;
  private VBox serviceListContainer;
  private Map<String, ServiceUI> servicesUIMap = new HashMap<>();

  public ServiceListUI(Stage primaryStage) {
    this.serviceListContainer = new VBox();
    this.serviceListContainer.setStyle("-fx-border-color: #2e8b57;-fx-border-width: 2px;-fx-padding: 10;-fx-spacing: 8;");

    serviceEditorUI = new ServiceEditorUI(primaryStage);
    applicationModel = new ApplicationModel();
    serviceEditorUI.setEditedServiceCallback(this::applyEditedService);

    Button addServiceButton = new Button("add service");
    addServiceButton.setOnMouseClicked(event -> {
      serviceEditorUI.show(new Service());
    });
    serviceListContainer.getChildren().add(addServiceButton);
  }

  public VBox getServiceListContainer() {
    return serviceListContainer;
  }

  private void applyEditedService(Service service) {
    int indexOf = applicationModel.getServices().indexOf(service);
    if (indexOf != -1) {
      applicationModel.getServices().set(indexOf, service);
      ServiceUI serviceUI = servicesUIMap.get(service.getId());
      serviceUI.setService(service);
    } else {
      applicationModel.getServices().add(service);
      ServiceUI serviceUI = new ServiceUI();
      serviceUI.setService(service);
      serviceUI.setStartServiceExecutor(() -> startService(service));
      serviceUI.setEditServiceExecutor(() -> editService(service));
      serviceUI.setDeleteServiceExecutor(() -> deleteService(service));
      servicesUIMap.put(service.getId(), serviceUI);
      serviceListContainer.getChildren().add(serviceUI.getContainer());
    }
  }

  private void deleteService(Service service) {
    ServiceUI serviceUI = servicesUIMap.remove(service.getId());
    Iterator<Node> iterator = serviceListContainer.getChildren().iterator();
    while (iterator.hasNext()) {
      Node node = iterator.next();
      if (node.equals(serviceUI.getContainer())) {
        iterator.remove();
        break;
      }
    }
    applicationModel.getServices().remove(service);
  }

  private void editService(Service service) {
    serviceEditorUI.show(service);
  }

  private void startService(Service service) {

  }
}
