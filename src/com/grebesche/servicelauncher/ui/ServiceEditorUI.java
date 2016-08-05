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
  private Service currentService;
  private final Stage dialog;


  public ServiceEditorUI(Stage primaryStage) {
    this.primaryStage = primaryStage;

    dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox dialogVbox = new VBox(20);

    // name
    HBox nameHBox = new HBox();
    Label nameLabel = new Label("name");
    nameTextField = new TextField();

    nameHBox.getChildren().add(nameLabel);
    nameHBox.getChildren().add(nameTextField);
    dialogVbox.getChildren().add(nameHBox);

    Button addStepButton = new Button("add step");
    addStepButton.setOnMouseClicked(event -> addStep(new ExecutionStep("", "")));
    dialogVbox.getChildren().add(addStepButton);

    stepsContainer = new VBox();
    dialogVbox.getChildren().add(stepsContainer);

    addActionButtons(dialog, dialogVbox);
    Scene dialogScene = new Scene(dialogVbox, 500, 500);
    dialog.setScene(dialogScene);
  }

  public void setEditedServiceCallback(EditedServiceCallback editedServiceCallback) {
    this.editedServiceCallback = editedServiceCallback;
  }

  public void show(Service service) {

    clear();
    currentService = service;

    nameTextField.setText(service.getName());
    if (service.getExecutionSteps().size() == 0) {
      // bootstrap 1 empty step
      addStep(new ExecutionStep("", ""));
    } else {
      service.getExecutionSteps().forEach(this::addStep);
    }
    dialog.show();
  }

  private void clear() {
    nameTextField.setText("");
    stepsContainer.getChildren().clear();
    stepsMap.clear();
    currentNumberOfSteps = 0;
  }

  private void addActionButtons(Stage dialog, VBox dialogVbox) {
    HBox actionHBox = new HBox();
    Button okButton = new Button("Ok");
    okButton.setOnMouseClicked(event -> {
      if(currentService != null) {
        fillServiceWithUIData();
        editedServiceCallback.serviceEdited(currentService);
      }
      clear();
      dialog.close();
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnMouseClicked(event -> dialog.close());
    actionHBox.getChildren().add(okButton);
    actionHBox.getChildren().add(cancelButton);
    dialogVbox.getChildren().add(actionHBox);
  }

  private void fillServiceWithUIData() {
    currentService.setName(nameTextField.getText());
    currentService.getExecutionSteps().clear();
    for (ServiceStepEditorUI step : getSortedSteps()) {
      currentService.getExecutionSteps().add(new ExecutionStep(
          step.getFolder(), step.getCommand()));
    }
  }

  private List<ServiceStepEditorUI> getSortedSteps() {
    List<ServiceStepEditorUI> steps = new ArrayList<>(stepsMap.values());
    Collections.sort(steps, (o1, o2) -> o1.getStepNumber().compareTo(o2.getStepNumber()));
    return steps;
  }

  private void addStep(ExecutionStep step) {
    currentNumberOfSteps++;
    ServiceStepEditorUI serviceStepEditorUI = new ServiceStepEditorUI(currentNumberOfSteps, step, primaryStage);
    serviceStepEditorUI.setDeleteStepCallback(() -> {
      stepsContainer.getChildren().remove(serviceStepEditorUI.getContainer());
      stepsMap.remove(step.getId());
      recalculateStepsNumbers();
    });
    stepsMap.put(step.getId(), serviceStepEditorUI);
    stepsContainer.getChildren().add(serviceStepEditorUI.getContainer());
  }

  private void recalculateStepsNumbers() {
    List<ServiceStepEditorUI> sortedSteps = getSortedSteps();
    for (int i = 1; i < (sortedSteps.size() + 1); i++) {
      sortedSteps.get(i - 1).setStepNumber(i);
    }
  }
}
