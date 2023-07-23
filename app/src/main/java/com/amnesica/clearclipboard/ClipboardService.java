package com.amnesica.clearclipboard;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;

public class ClipboardService extends Service {

    private ClearnClipboard clearnClipboard;

    @Override
    public void onCreate() {
        super.onCreate();
        clearnClipboard = new ClearnClipboard(this, new Handler(Looper.getMainLooper()));
        clearnClipboard.clearn();
        // stop service
        this.stopSelf();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
