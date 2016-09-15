package com.webinar.android.hellowebinar.net;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by aktor on 15/09/16.
 */

public class Http {

    private OkHttpClient mClient;
    private static final String JSON_PLACEHOLDER="http://jsonplaceholder.typicode.com";

    private Http(){
        mClient = new OkHttpClient();
    }


    public Call saveNotesToServer(JSONObject json) throws IOException {
        Request request = new Request.Builder()
                .url(JSON_PLACEHOLDER+"/notes")
                .post(
                        RequestBody.create(
                                MediaType.parse("application/json")
                        ,json.toString()))
                .build();
        Call call = mClient.newCall(request);
        Response execute = call.execute();
        return null;
    }

    public Call getPostsCall(){
        Request request = new Request.Builder()
                .url(JSON_PLACEHOLDER+"/posts")
                .get()
                .build();
        Call call = mClient.newCall(request);
        return call;
    }

    public JSONObject getPostsSync() throws IOException, JSONException {
        Call postsCall = getPostsCall();
        Response execute = postsCall.execute();
        if (execute.isSuccessful()){
            ResponseBody body = execute.body();
            String string = body.string();
            return new JSONObject(string);
        } else {
            throw new IOException(execute.message());
        }
    }

}
