package com.example.android.voicetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button mic;
    private TextView op;
    private final int REQ_CODE_SPEECH_OUTPUT=143;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        op=findViewById(R.id.text);
        mic=findViewById(R.id.mic);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_mic();
            }
        });
    }

    public void open_mic(){
        Intent voice=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak now");

        try{
            startActivityForResult(voice,REQ_CODE_SPEECH_OUTPUT);
        }
        catch(ActivityNotFoundException t){

        }


    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode){
            case REQ_CODE_SPEECH_OUTPUT:{
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String> voiceinText=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    op.setText(voiceinText.get(0));
                }
                break;
            }
        }
    }

}
