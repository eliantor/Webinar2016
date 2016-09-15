package com.webinar.android.hellowebinar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;

/**
 * Created by aktor on 15/09/16.
 */

public class Files {


    public void useFiles(Context context){
        File filesDir = context.getFilesDir();
        File cacheDir = context.getCacheDir();

        context.getDatabasePath("CIAO.db");

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File directory = context.getExternalFilesDir("DIRECTORY");

        SharedPreferences sp = context.getSharedPreferences("MY_PREFERENCES",Context.MODE_PRIVATE);
        boolean wants_notifications = sp.getBoolean("WANTS_NOTIFICATIONS", false);
        sp.edit()
          .putBoolean("WANTS_NOTIFICATIONS",true)
          .apply();//async

        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            }
        });


    }
}
