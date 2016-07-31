package com.grebesche.servicelauncher.model;

import java.util.ArrayList;
import java.util.List;

public class ApplicationModel {

  private List<Service> services = new ArrayList<>();

  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }
}
