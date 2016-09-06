package com.webinar.android.hellowebinar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {

    private static final String PARAM_KEY = "PARAM_KEY";
    private static final String PARAM_STARTED_FOR_RESULT="PARAM_STARTED_FOR_RESULT";

    public static void startMe(Activity activity, String message){
        Intent intent = new Intent(activity,SecondActivity.class);
        intent.putExtra(PARAM_KEY,message);
        activity.startActivity(intent);
    }

    public static void startForResult(Activity activity,String message,int requestCode){
        Intent intent = new Intent(activity,SecondActivity.class);
        intent.putExtra(PARAM_KEY,message);
        intent.putExtra(PARAM_STARTED_FOR_RESULT,true);
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(PARAM_KEY);
        TextView viewById =(TextView) findViewById(R.id.displayResult);
        viewById.setText(stringExtra);

        findViewById(R.id.respond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
