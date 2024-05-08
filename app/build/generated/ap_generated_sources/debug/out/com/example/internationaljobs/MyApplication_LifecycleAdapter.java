package com.example.internationaljobs;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MethodCallsLogger;
import java.lang.Override;

public class MyApplication_LifecycleAdapter implements GeneratedAdapter {
  final MyApplication mReceiver;

  MyApplication_LifecycleAdapter(MyApplication receiver) {
    this.mReceiver = receiver;
  }

  @Override
  public void callMethods(LifecycleOwner owner, Lifecycle.Event event, boolean onAny,
      MethodCallsLogger logger) {
    boolean hasLogger = logger != null;
    if (onAny) {
      return;
    }
    if (event == Lifecycle.Event.ON_START) {
      if (!hasLogger || logger.approveCall("onMoveToForeground", 1)) {
        mReceiver.onMoveToForeground();
      }
      return;
    }
  }
}
