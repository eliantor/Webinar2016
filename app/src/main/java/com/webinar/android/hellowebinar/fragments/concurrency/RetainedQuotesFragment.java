package com.webinar.android.hellowebinar.fragments.concurrency;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by aktor on 14/09/16.
 */

public class RetainedQuotesFragment extends Fragment implements RandomQuoteAsyncTask.OnNewQuoteListener {


    public interface OnCompleteListner {
        public void onComplete(String completion);
    }

    private OnCompleteListner mListener;
    private String mPendingResult;
    private boolean mReady;

    private RandomQuoteAsyncTask mTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnCompleteListner)getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        mReady = true;
        dispatchPending();
    }


    @Override
    public void onPause() {
        super.onPause();
        mReady = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePendingResources();
    }


    public void getQuote(){
        if (mTask == null){
            mTask = new RandomQuoteAsyncTask(this);
            mTask.execute(20000L);
        }
    }

    private void releasePendingResources(){
        mPendingResult = null;
    }

    private void dispatchPending(){
        dispatchResult(mPendingResult);
    }

    private void dispatchResult(String result){
        mPendingResult = result;
        if (mListener!=null && mReady){
            String res = mPendingResult;
            mListener.onComplete(res);
            mPendingResult = null;
        }
    }

    @Override
    public void onNewQuote(String quote) {
        dispatchResult(quote);
        mTask = null;
    }

}
