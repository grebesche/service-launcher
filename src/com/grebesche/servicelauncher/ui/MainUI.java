package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.model.ApplicationModel;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainUI {

  private ApplicationModel applicationModel;
  private ServiceEditorUI serviceEditorUI;
  private VBox mainLayout;
  private Map<String, ServiceUI> servicesUIMap = new HashMap<>();

  public void start(Stage primaryStage) throws Exception {
    serviceEditorUI = new ServiceEditorUI(primaryStage);
    applicationModel = new ApplicationModel();
    serviceEditorUI.setEditedServiceCallback(this::applyEditedService);
    mainLayout = new VBox();

    Button addServiceButton = new Button("add service");
    addServiceButton.setOnMouseClicked(event -> {
      serviceEditorUI.show(null);
    });
    mainLayout.getChildren().add(addServiceButton);

    primaryStage.setTitle("Service launcher");
    primaryStage.setScene(new Scene(mainLayout, 300, 275));
    primaryStage.show();
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
      mainLayout.getChildren().add(serviceUI.getContainer());
    }
  }

  private void deleteService(Service service) {
    ServiceUI serviceUI = servicesUIMap.remove(service.getId());
    Iterator<Node> iterator = mainLayout.getChildren().iterator();
    while (iterator.hasNext()) {
      Node node = iterator.next();
      if(node.equals(serviceUI.getContainer())) {
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
