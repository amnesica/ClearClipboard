package com.amnesica.clearclipboard;


import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class ClearnClipboard {

    private final Context context;
    private final Handler handler;


    public ClearnClipboard(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;

    }

    public void clearn() {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager == null) return;

        try {
            clipboardManager.clearPrimaryClip();
            showToastClearingClipboardWasSuccessful();
        } catch (Exception e) {
            Log.d(ClipboardService.class.getSimpleName(), String.valueOf(e));
            showToastClearingClipboardFailed();

        }
    }

    private void showToastClearingClipboardWasSuccessful() {
        runOnUiThread(() -> Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.clipboardDeleted) + context.getResources().getString(R.string.unicodeCactus), Toast.LENGTH_SHORT).show());
    }

    private void showToastClearingClipboardFailed() {
        runOnUiThread(() -> Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.clipboardDeletedNotSuccessful), Toast.LENGTH_SHORT).show());
    }

    private void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
