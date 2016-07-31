package com.grebesche.servicelauncher.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {


  private String name;

  private List<ExecutionStep> executionSteps = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addStep(String path, String command) {
    executionSteps.add(new ExecutionStep(path, command));
  }

  public List<ExecutionStep> getExecutionSteps() {
    return Collections.unmodifiableList(executionSteps);
  }

  public void start() {
    executionSteps.forEach(ExecutionStep::start);
  }
}



