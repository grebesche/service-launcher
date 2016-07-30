package com.grebesche.servicelauncher.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {


  private String name;
  private String path;
  private List<ExecutionStep> executionSteps = new ArrayList<>();

  public void addStep(String command){
    executionSteps.add(new ExecutionStep(command));
  }

  public void start() {
    executionSteps.forEach(ExecutionStep::start);
  }
}



