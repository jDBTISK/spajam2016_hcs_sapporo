package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Recording extends Activity implements View.OnClickListener{
    int i = 0;

    MediaManipulation mm = new MediaManipulation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        Button recordingStart = (Button)findViewById(R.id.recordingstart);
        Button recordingStop = (Button)findViewById(R.id.recordingstop);
        Button saiseiStart = (Button)findViewById(R.id.button2);
        Button saiseiStop = (Button)findViewById(R.id.button3);

        recordingStart.setOnClickListener(this);
        recordingStop.setOnClickListener(this);
        saiseiStart.setOnClickListener(this);
        saiseiStop.setOnClickListener(this);

    }

    public void onClick(View v){

        switch (v.getId()) {
            case R.id.recordingstart:
                mm.recordingStart(i);
                i++;
                Log.i("RECORDING","START"+i);
                break;
            case R.id.recordingstop:
                mm.recordingStop();
                Log.i("RECORDING","STop"+i);
                break;
            case R.id.button2:
                mm.playStart(i-1);
                Log.i("PLAY","START"+i);
                break;
            case R.id.button3:
                mm.playStop();
                Log.i("PLAY","STop"+i);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mm.close();
    }
}