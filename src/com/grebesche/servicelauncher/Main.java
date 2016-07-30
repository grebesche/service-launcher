package com.grebesche.servicelauncher;

import com.grebesche.servicelauncher.actions.StartServiceExecutor;
import com.grebesche.servicelauncher.pref.ServicePreferences;
import com.grebesche.servicelauncher.pref.UserPreferences;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application implements StartServiceExecutor{


  @Override
  public void start(Stage primaryStage) throws Exception {
    UserPreferences userPreferences = new UserPreferences();

    VBox stackPane = new VBox();

    Button addModuleButton = new Button("add module");
    addModuleButton.setOnMouseClicked(event -> {
      DirectoryChooser chooser = new DirectoryChooser();
      chooser.setTitle("Module folder");
      File selectedDirectory = chooser.showDialog(primaryStage);

      ServicePreferences module = new ServicePreferences();
      module.setPath(selectedDirectory.getAbsolutePath());
      module.setFolderName(selectedDirectory.getName());
      userPreferences.getServices().add(module);
      addModuleToUI(module, stackPane);
    });
    stackPane.getChildren().add(addModuleButton);

    primaryStage.setTitle("Service launcher");
    primaryStage.setScene(new Scene(stackPane, 300, 275));
    primaryStage.show();
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
