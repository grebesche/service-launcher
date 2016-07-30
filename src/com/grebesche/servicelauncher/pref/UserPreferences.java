package com.grebesche.servicelauncher.pref;

import java.util.ArrayList;
import java.util.List;

public class UserPreferences {

  private List<ServicePreferences> services = new ArrayList<>();

  public List<ServicePreferences> getServices() {
    return services;
  }

  public void setServices(List<ServicePreferences> services) {
    this.services = services;
  }
}
