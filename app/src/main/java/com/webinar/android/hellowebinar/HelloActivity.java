package com.webinar.android.hellowebinar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.webinar.android.hellowebinar.data.Student;

/**
 * Created by aktor on 06/09/16.
 */
public class HelloActivity extends Activity implements View.OnClickListener{

    private static final int REQUEST_CODE = 1;

    private static final String TAG = "HelloActivity";

    private static final String TEXT_KEY = "TextKEY";
    private EditText mInput;
    private TextView mOutput;
    private String mText;

    public HelloActivity(){
        mText = "CIAO";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");


        setContentView(R.layout.hello_activity);
        TextView viewById =(TextView) findViewById(R.id.textView);
        viewById.setText("CIAO CODE");


        mInput = (EditText)findViewById(R.id.input);
        mOutput = (TextView)findViewById(R.id.output);

        if (savedInstanceState == null) {
            mText = "Fresh";
        } else {
            mText = savedInstanceState.getString(TEXT_KEY);
        }
        mOutput.setText(mText);


        Button launch = (Button)findViewById(R.id.launch);
        launch.setOnClickListener(this);


        Button button = (Button)findViewById(R.id.clickMe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+"CLICKED");
                updateText();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.launch:
                launchSecondActivity();
                break;
        }
    }

    private void launchSecondActivity() {
//        SecondActivity.startMe(this,mInput.getText().toString());
//        Intent explicit = new Intent((Context)this,SecondActivity.class);
//        explicit.putExtra("KEY",mInput.getText().toString());
//
//        this.startActivity(explicit);

        SecondActivity.startForResult(this,mInput.getText().toString(),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE){
            if (resultCode==RESULT_OK){
                Toast.makeText(this,"RESULT OK",Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this,"RESULT FAIL",Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_KEY,mText);
//        outState.putParcelable("STUDENT",new Student("Andrea",2));
//        Student student = outState.getParcelable("STUDENT");

    }

    private void updateText(){
        mText = mInput.getText().toString();
        mOutput.setText(mText);
        mInput.setText(null);
    }

}
