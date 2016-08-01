package com.grebesche.servicelauncher.ui;

import com.grebesche.servicelauncher.actions.EditedServiceCallback;
import com.grebesche.servicelauncher.model.Service;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class ServiceEditorUI {

  private Stage primaryStage;
  private EditedServiceCallback editedServiceCallback;

  public ServiceEditorUI(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public void setEditedServiceCallback(EditedServiceCallback editedServiceCallback) {
    this.editedServiceCallback = editedServiceCallback;
  }

  public void show(Service service) {
    final Service finalService = service != null ? service : new Service();

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox dialogVbox = new VBox(20);

    // name
    HBox nameHBox = new HBox();
    Label nameLabel = new Label("name");
    TextField nameTextField = new TextField();
    nameTextField.setText(finalService.getName());
    nameHBox.getChildren().add(nameLabel);
    nameHBox.getChildren().add(nameTextField);
    dialogVbox.getChildren().add(nameHBox);

    // step 1
    VBox stepVBox = new VBox();

    stepVBox.getChildren().add(new Label("Step 1"));

    HBox stepFolderHBox = new HBox();
    Label stepFolderLabel = new Label("directory");
    TextField stepFolderField = new TextField();
    stepFolderHBox.getChildren().add(stepFolderLabel);
    stepFolderHBox.getChildren().add(stepFolderField);
    Button stepChooseDirectoryButton = new Button("Choose directory");
    stepChooseDirectoryButton.setOnMouseClicked(event -> {
      DirectoryChooser chooser = new DirectoryChooser();
      chooser.setTitle("Module folder");
      File selectedDirectory = chooser.showDialog(primaryStage);
      stepFolderField.setText(selectedDirectory.getAbsolutePath());
    });
    stepFolderHBox.getChildren().add(stepChooseDirectoryButton);
    stepVBox.getChildren().add(stepFolderHBox);

    HBox commandHBox = new HBox();
    Label commandLabel = new Label("command");
    TextField commandTextField = new TextField();
    commandHBox.getChildren().add(commandLabel);
    commandHBox.getChildren().add(commandTextField);
    stepVBox.getChildren().add(commandHBox);

    HBox actionHBox = new HBox();
    Button okButton = new Button("Ok");
    okButton.setOnMouseClicked(event -> {
      finalService.addStep(stepFolderField.getText(), commandTextField.getText());
      finalService.setName(nameTextField.getText());
      editedServiceCallback.serviceEdited(finalService);
      dialog.close();
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnMouseClicked(event -> dialog.close());
    actionHBox.getChildren().add(okButton);
    actionHBox.getChildren().add(cancelButton);
    stepVBox.getChildren().add(actionHBox);

    dialogVbox.getChildren().add(stepVBox);

    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }
}
