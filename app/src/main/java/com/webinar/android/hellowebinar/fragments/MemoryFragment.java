package com.webinar.android.hellowebinar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by aktor on 14/09/16.
 */

public class MemoryFragment extends Fragment{
    private static final String TAG = "MemoryFragment";

    private int mValue;
    private InputStream mInput;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "memory: "+this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // <--
        Log.d(TAG, "memory: "+this);
        mValue = 10;
        mInput = open();
    }

    private InputStream open(){
        return null; //....
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        // non verrà chiamato
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void incrementAndLog() {
        mValue++;
        Log.d(TAG, "Memory: counter is "+mValue);
    }
}
