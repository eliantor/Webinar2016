package com.webinar.android.hellowebinar;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import org.json.JSONObject;

/**
 * Created by aktor on 15/09/16.
 */

public class UploadNotesService extends IntentService {

    public UploadNotesService() {
        super("UPLOAD_SERVICE");
    }

    public static void startMe(Context context){
        Intent intent = new Intent(context,UploadNotesService.class);
        intent.putExtra("SAVE_URGENT",true);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // viene eseguito su un thread separato
        // chiedo quali dati sono ancora da salvare
        // ad un CP
        ContentResolver resolver=getContentResolver();
        //resolver.query() //query unsaved
        // sincronizzo i dati con la rete
        // informo il CP che i dati sono salvati

        //
        JSONObject postRequest = fromCursor();

    }

    private JSONObject fromCursor(){
        return null;
    }
}
