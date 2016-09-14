package com.webinar.android.hellowebinar.fragments.concurrency;

import android.os.AsyncTask;

import com.webinar.android.hellowebinar.data.RandomQuotes;

/**
 * Created by aktor on 14/09/16.
 */

public class RandomQuoteAsyncTask extends AsyncTask<Long,Void,String> {

    public interface OnNewQuoteListener {
        public void onNewQuote(String quote);
    }

    private OnNewQuoteListener mListener;

    public RandomQuoteAsyncTask(OnNewQuoteListener listener){
        mListener = listener;
    }

    @Override
    protected String doInBackground(Long... params) {
//        publishProgress(null);
        String randomQuoteIn = RandomQuotes.getRandomQuoteIn(params[0]);
        return randomQuoteIn;
    }

//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mListener.onNewQuote(result);

    }
}
