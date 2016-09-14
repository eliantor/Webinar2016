package com.webinar.android.hellowebinar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webinar.android.hellowebinar.R;
import com.webinar.android.hellowebinar.data.RandomQuotes;
import com.webinar.android.hellowebinar.fragments.concurrency.RandomQuoteAsyncTask;
import com.webinar.android.hellowebinar.fragments.concurrency.RetainedQuotesFragment;

/**
 * Created by aktor on 14/09/16.
 */

public class ActivityWithRetainedFragments extends AppCompatActivity

    implements RetainedQuotesFragment.OnCompleteListner{
    private static final String TAG = "ActivityWithRetainedFra";


    private TextView mOutput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Who am I? "+this);
        setContentView(R.layout.activity_withretain);
        Button button = (Button)findViewById(R.id.increment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });

        Button randomQuote = (Button)findViewById(R.id.get_quote);
        mOutput = (TextView)findViewById(R.id.output);
        randomQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandoQuoteSync();
            }
        });
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(new RetainedQuotesFragment(),"QUOTES")
                    .add(new MemoryFragment(), "MEMORY")
                    .add(new NonRetained(), "NON_RETAINED")
                    .commit();
        }

    }



    private static class RandomQuoteThreads extends Thread{

        @Override
        public void run() {
            super.run();

        }
    }



    private void getRandoQuoteSync(){
        FragmentManager fm = getSupportFragmentManager();
        RetainedQuotesFragment quotes =(RetainedQuotesFragment) fm.findFragmentByTag("QUOTES");
        quotes.getQuote();

//        new Thread(){
//            @Override
//            public void run() {
//
//                final String randomQuote = RandomQuotes.getRandomQuoteIn(20000l);
//
//                ActivityWithRetainedFragments.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mOutput.setText(randomQuote);
//                    }
//                });
//
//            }
//        }.start();
//
    }

    @Override
    public void onComplete(String completion) {
        mOutput.setText(completion);
    }

    private void increment(){
        FragmentManager fm  = getSupportFragmentManager();
        MemoryFragment memory =(MemoryFragment) fm.findFragmentByTag("MEMORY");
        memory.incrementAndLog();

        NonRetained nonRetained = (NonRetained)fm.findFragmentByTag("NON_RETAINED");
        nonRetained.incrementAndLog();

    }



}
