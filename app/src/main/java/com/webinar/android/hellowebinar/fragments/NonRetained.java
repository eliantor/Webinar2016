package com.webinar.android.hellowebinar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by aktor on 14/09/16.
 */

public class NonRetained extends Fragment {
    private static final String TAG = "NonRetained";
    private int mValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Fragment is: "+this);
        mValue = 0;
    }

        public void incrementAndLog() {
            mValue++;
            Log.d(TAG, "Non Retained: counter is "+mValue);
        }
}
