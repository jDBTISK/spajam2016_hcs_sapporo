package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Recording extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        Button recordingStart = (Button)findViewById(R.id.recordingstart);
        Button recordingStop = (Button)findViewById(R.id.recordingstop);

        recordingStart.setOnClickListener(this);
        recordingStop.setOnClickListener(this);
    }

    public void onClick(View v){
        MediaManipulation mm = new MediaManipulation();
        switch (v.getId()) {
            case R.id.recordingstart:
//                mm.recordingStart();
                break;
            case R.id.recordingstop:
                mm.recordingStop();
                mm.close();
                break;
            default:
                break;
        }
    }
}
