package com.grebesche.servicelauncher.model;

import java.io.IOException;

public class ExecutionStep {

  private Process process;
  private String path;
  private String command;

  public ExecutionStep(String path, String command) {
    this.path = path;
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
