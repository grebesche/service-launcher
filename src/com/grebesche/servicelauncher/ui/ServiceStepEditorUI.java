package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.DeleteStepCallback;
import com.grebesche.servicelauncher.model.ExecutionStep;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
  private DeleteStepCallback deleteStepCallback;

  public ServiceStepEditorUI(int stepNumber, ExecutionStep step, Stage primaryStage) {
    this.stepNumber = stepNumber;
    container = new VBox();
    container.getChildren().add(new Label("Step " + stepNumber));

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
      if(deleteStepCallback != null) deleteStepCallback.delete();
    });
    container.getChildren().add(delete);
  }

  public void setDeleteStepCallback(DeleteStepCallback deleteStepCallback) {
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
}
