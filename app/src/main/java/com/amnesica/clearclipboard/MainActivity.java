package com.amnesica.clearclipboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start service
        startService(new Intent(this, ClipboardService.class));

        // finish activity immediately
        finish();
    }
}
