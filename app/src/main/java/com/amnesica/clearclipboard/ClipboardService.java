package com.amnesica.clearclipboard;

import androidx.annotation.Nullable;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class ClipboardService extends Service {

    private Handler handler;

    @Override
    public void onCreate() {
        // Handler will get associated with the current thread,
        // which is the main thread.
        handler = new Handler();
        super.onCreate();

        //clear clipboard
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            clipboardManager.clearPrimaryClip();

            //show toast on UI thread
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
        }

        //stop Service
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