package com.webinar.android.hellowebinar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webinar.android.hellowebinar.R;

import java.util.Random;

/**
 * Created by aktor on 13/09/16.
 */

public class ColorFragment extends Fragment {

    private int mNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.color_fragment,container,false);
        TextView textArea = (TextView)view.findViewById(R.id.random);

        if (savedInstanceState == null ){
            Random random = new Random();
            mNumber =random.nextInt(1000);

        } else {
            mNumber = savedInstanceState.getInt("NUMBER");
        }
        textArea.setText("Number "+mNumber);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("NUMBER",mNumber);
    }
}
