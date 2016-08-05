package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.ActionCallback;
import com.grebesche.servicelauncher.model.ExecutionStep;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ServiceStepEditorUI {

  private int stepNumber = 0;
  private VBox container;
  private TextField folderField;
  private TextField commandTextField;
  private Label stepNumberLabel;
  private ActionCallback deleteStepCallback;

  public ServiceStepEditorUI(int stepNumber, ExecutionStep step, Stage primaryStage) {

    container = new VBox();
    stepNumberLabel = new Label();
    this.stepNumber = stepNumber;
    setStepNumberLabel();
    container.getChildren().add(stepNumberLabel);

    HBox stepFolderHBox = new HBox();
    Label stepFolderLabel = new Label("directory");
    folderField = new TextField();
    folderField.setText(step.getPath());
    stepFolderHBox.getChildren().add(stepFolderLabel);
    stepFolderHBox.getChildren().add(folderField);
    Button stepChooseDirectoryButton = new Button("Choose directory");
    stepChooseDirectoryButton.setOnMouseClicked(event -> {
      DirectoryChooser chooser = new DirectoryChooser();
      chooser.setTitle("Module folder");
      File selectedDirectory = chooser.showDialog(primaryStage);
      folderField.setText(selectedDirectory.getAbsolutePath());
    });
    stepFolderHBox.getChildren().add(stepChooseDirectoryButton);
    container.getChildren().add(stepFolderHBox);

    HBox commandHBox = new HBox();
    Label commandLabel = new Label("command");
    commandTextField = new TextField();
    commandTextField.setText(step.getCommand());
    commandHBox.getChildren().add(commandLabel);
    commandHBox.getChildren().add(commandTextField);
    container.getChildren().add(commandHBox);

    Button delete = new Button("delete");
    delete.setOnMouseClicked(event -> {
      if (deleteStepCallback != null) deleteStepCallback.execute();
    });
    container.getChildren().add(delete);
  }

  public void setDeleteStepCallback(ActionCallback deleteStepCallback) {
    this.deleteStepCallback = deleteStepCallback;
  }

  public VBox getContainer() {
    return container;
  }

  public String getFolder() {
    return folderField.getText();
  }

  public String getCommand() {
    return commandTextField.getText();
  }

  public Integer getStepNumber() {
    return stepNumber;
  }

  public void setStepNumber(int stepNumber) {
    this.stepNumber = stepNumber;
    setStepNumberLabel();
  }

  private void setStepNumberLabel() {
    stepNumberLabel.setText("Step " + stepNumber);
  }
}
