package com.amnesica.clearclipboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      startService(new Intent(this, ClipboardService.class));
    } catch (Exception e) {
      Log.d(MainActivity.class.getSimpleName(), String.valueOf(e));
    }

    // finish activity immediately
    finish();
  }
}
