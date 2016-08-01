package com.grebesche.servicelauncher.actions;

import com.grebesche.servicelauncher.model.Service;

@FunctionalInterface
public interface EditedServiceCallback {
  void serviceEdited(Service service);
}
