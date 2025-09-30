package com.amnesica.clearclipboard;

import android.os.Handler;
import android.os.Looper;
import android.service.quicksettings.TileService;
import android.util.Log;

public class ClipboardQuickSettingsService extends TileService {

    private Handler handler;
    private ClearnClipboard clearnClipboard;
    private final String TAG = "QuickService";

    @Override
    public void onStartListening() {
        super.onStartListening();
        handler = new Handler(Looper.getMainLooper());
        Log.i(TAG, "onStartListening()");

    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.i(TAG, "onStopListening()");
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Log.i(TAG, "onTileAdded()");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.i(TAG, "onTileRemoved()");
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.i(TAG, "onClick()");
        clearnClipboard = new ClearnClipboard(this, new Handler(Looper.getMainLooper()));
        clearnClipboard.clearn();
    }


}