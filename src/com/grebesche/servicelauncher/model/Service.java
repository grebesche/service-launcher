package com.grebesche.servicelauncher.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Service {

  private String id;
  private String name;

  private List<ExecutionStep> executionSteps = new ArrayList<>();

  public Service() {
    id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ExecutionStep> getExecutionSteps() {
    return executionSteps;
  }

  public void start() {
    executionSteps.forEach(ExecutionStep::start);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Service service = (Service) o;

    return id.equals(service.id);

  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}



