package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.EditedServiceCallback;
import com.grebesche.servicelauncher.model.ExecutionStep;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceEditorUI {

  private Stage primaryStage;
  private EditedServiceCallback editedServiceCallback;
  private VBox stepsContainer;
  private TextField nameTextField;
  private Map<String, ServiceStepEditorUI> stepsMap = new HashMap<>();
  private int currentNumberOfSteps = 0;

  public ServiceEditorUI(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public void setEditedServiceCallback(EditedServiceCallback editedServiceCallback) {
    this.editedServiceCallback = editedServiceCallback;
  }

  public void show(Service service) {

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox dialogVbox = new VBox(20);

    // name
    HBox nameHBox = new HBox();
    Label nameLabel = new Label("name");
    nameTextField = new TextField();
    nameTextField.setText(service.getName());
    nameHBox.getChildren().add(nameLabel);
    nameHBox.getChildren().add(nameTextField);
    dialogVbox.getChildren().add(nameHBox);

    Button addStepButton = new Button("add step");
    addStepButton.setOnMouseClicked(event -> addStep(new ExecutionStep("", "")));
    dialogVbox.getChildren().add(addStepButton);

    stepsContainer = new VBox();

    if (service.getExecutionSteps().size() == 0) {
      // bootstrap 1 empty step
      addStep(new ExecutionStep("", ""));
    } else {
      service.getExecutionSteps().forEach(this::addStep);
    }

    dialogVbox.getChildren().add(stepsContainer);

    addActionButtons(service, dialog, dialogVbox);

    Scene dialogScene = new Scene(dialogVbox, 500, 500);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  private void addActionButtons(Service service, Stage dialog, VBox dialogVbox) {
    HBox actionHBox = new HBox();
    Button okButton = new Button("Ok");
    okButton.setOnMouseClicked(event -> {
      populateServiceData(service);
      editedServiceCallback.serviceEdited(service);
      dialog.close();
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnMouseClicked(event -> dialog.close());
    actionHBox.getChildren().add(okButton);
    actionHBox.getChildren().add(cancelButton);
    dialogVbox.getChildren().add(actionHBox);
  }

  private void populateServiceData(Service finalService) {
    finalService.setName(nameTextField.getText());

    List<ServiceStepEditorUI> steps = new ArrayList<>(stepsMap.values());
    Collections.sort(steps, (o1, o2) -> o1.getStepNumber().compareTo(o2.getStepNumber()));
    finalService.getExecutionSteps().clear();
    for (ServiceStepEditorUI step : steps) {
      finalService.getExecutionSteps().add(new ExecutionStep(
          step.getFolder(), step.getCommand()));
    }
  }

  private void addStep(ExecutionStep step) {
    currentNumberOfSteps++;
    ServiceStepEditorUI serviceStepEditorUI = new ServiceStepEditorUI(currentNumberOfSteps, step, primaryStage);
    serviceStepEditorUI.setDeleteStepCallback(() -> {
      stepsContainer.getChildren().remove(serviceStepEditorUI.getContainer());
      stepsMap.remove(step.getId());
    });
    stepsMap.put(step.getId(), serviceStepEditorUI);
    stepsContainer.getChildren().add(serviceStepEditorUI.getContainer());
  }
}
