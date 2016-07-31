package com.grebesche.servicelauncher;

import com.grebesche.servicelauncher.actions.StartServiceExecutor;
import com.grebesche.servicelauncher.model.ApplicationModel;
import com.grebesche.servicelauncher.model.Service;
import com.grebesche.servicelauncher.pref.ServicePreferences;
import javafx.application.Application;
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

public class Main extends Application implements StartServiceExecutor {

  private ApplicationModel applicationModel;

  @Override
  public void start(Stage primaryStage) throws Exception {

    applicationModel = new ApplicationModel();

    VBox stackPane = new VBox();

    Button addModuleButton = new Button("add module");
    addModuleButton.setOnMouseClicked(event -> {
      /*DirectoryChooser chooser = new DirectoryChooser();
      chooser.setTitle("Module folder");
      File selectedDirectory = chooser.showDialog(primaryStage);*/

      /*ServicePreferences module = new ServicePreferences();
      module.setPath(selectedDirectory.getAbsolutePath());
      module.setFolderName(selectedDirectory.getName());
      userPreferences.getServices().add(module);

      Service service = new Service();
      service.addStep();
      applicationModel.getServices().add(service);

      addModuleToUI(module, stackPane);*/
      editServicePopup(primaryStage);
    });
    stackPane.getChildren().add(addModuleButton);

    primaryStage.setTitle("Service launcher");
    primaryStage.setScene(new Scene(stackPane, 300, 275));
    primaryStage.show();
  }

  private void editServicePopup(Stage primaryStage) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox dialogVbox = new VBox(20);

    // name
    HBox nameHBox = new HBox();
    Label nameLabel = new Label("name");
    TextField nameTextField = new TextField();
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
      Service service = new Service();
      service.addStep(stepFolderField.getText(), commandTextField.getText());
      applicationModel.getServices().add(service);
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

  private void addModuleToUI(ServicePreferences service, VBox stackPane) {
    HBox pane = new HBox();
    Label label = new Label(service.getFolderName());
    pane.getChildren().add(label);
    Button startButton = new Button("startService");
    pane.getChildren().add(startButton);
    startButton.setOnMouseClicked(event -> startService(service));
    stackPane.getChildren().add(pane);
  }

  @Override
  public void startService(ServicePreferences module) {

  }

  public static void main(String[] args) {
    launch(args);
  }
}
