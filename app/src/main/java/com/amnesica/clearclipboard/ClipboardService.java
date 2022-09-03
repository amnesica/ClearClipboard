package com.amnesica.clearclipboard;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ClipboardService extends Service {

  private Handler handler;

  @Override
  public void onCreate() {
    handler = new Handler(Looper.getMainLooper());
    super.onCreate();

    clearClipboard();

    // stop service
    this.stopSelf();
  }

  private void clearClipboard() {
    ClipboardManager clipboardManager =
        (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    if (clipboardManager != null) {
      try {
        clipboardManager.clearPrimaryClip();
        showToastClearingClipboardWasSuccessful();
      } catch (Exception e) {
        Log.d(ClipboardService.class.getSimpleName(), String.valueOf(e));
        showToastClearingClipboardFailed();
      }
    }
  }

  private void showToastClearingClipboardWasSuccessful() {
    runOnUiThread(
        new Runnable() {
          @Override
          public void run() {
            Toast.makeText(
                    getApplicationContext(),
                    getApplication().getResources().getString(R.string.clipboardDeleted)
                        + getApplication().getResources().getString(R.string.unicodeCactus),
                    Toast.LENGTH_SHORT)
                .show();
          }
        });
  }

  private void showToastClearingClipboardFailed() {
    runOnUiThread(
        new Runnable() {
          @Override
          public void run() {
            Toast.makeText(
                    getApplicationContext(),
                    getApplication()
                        .getResources()
                        .getString(R.string.clipboardDeletedNotSuccessful),
                    Toast.LENGTH_SHORT)
                .show();
          }
        });
  }

  private void runOnUiThread(Runnable runnable) {
    handler.post(runnable);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
