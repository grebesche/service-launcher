package com.grebesche.servicelauncher.model;

import java.io.IOException;

public class ExecutionStep {

  private Process process;
  private String command;

  public ExecutionStep(String command) {
    this.command = command;
  }

  public void start() {
    if (process != null) kill();
    try {
      process = Runtime.getRuntime().exec(command);
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
