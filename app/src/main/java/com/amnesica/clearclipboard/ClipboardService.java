package com.amnesica.clearclipboard;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ClipboardService extends Service {

    private Handler handler;

    @Override
    public void onCreate() {
        // Handler will get associated with the current thread,
        // which is the main thread.
        handler = new Handler(Looper.getMainLooper());
        super.onCreate();

        // get clipboard manager
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // check if clipboard manager is not null
        if (clipboardManager != null) {

            // try to clear clipboard when clipboardManager
            try {
                clipboardManager.clearPrimaryClip();

                // show toast on UI thread that clearing
                // clipboard content was successful
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                getApplication().getResources().getString(R.string.clipboardDeleted) +
                                        getApplication().getResources().getString(R.string.unicodeCat),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            } catch (Exception e) {
                // show toast on UI thread that clearing
                // clipboard content was not successful
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                getApplication().getResources().getString(R.string.clipboardDeletedNotSuccessful),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        }

        // stop Service
        this.stopSelf();
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