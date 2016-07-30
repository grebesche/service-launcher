package com.grebesche.servicelauncher.actions;

import com.grebesche.servicelauncher.pref.ServicePreferences;

public interface StartServiceExecutor {
  void startService(ServicePreferences module);
}
