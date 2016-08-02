package com.grebesche.servicelauncher.model;

import java.io.IOException;
import java.util.UUID;

public class ExecutionStep {

  private String id;
  private Process process;
  private String path;
  private String command;

  public ExecutionStep(String path, String command) {
    this.id = UUID.randomUUID().toString();
    this.path = path;
    this.command = command;
  }

  public String getId() {
    return id;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public void start() {
    if (process != null) kill();
    try {
      process = Runtime.getRuntime().exec(path + "/" + command);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void kill() {
    if (process != null) {
      process.destroy();
      process = null;
    }
  }
}
