package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Player extends Activity implements View.OnClickListener {
    int i = 0;

    Button saiseiStart, saiseiStop;
    MediaManipulation mm = new MediaManipulation();
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent=getIntent();
        pass=intent.getStringExtra("pass");
        saiseiStart = (Button) findViewById(R.id.button5);
        saiseiStop = (Button) findViewById(R.id.button6);

        saiseiStart.setOnClickListener(this);
        saiseiStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button5:
                mm.playStart(pass);

                Log.i("PLAY", "START" + i);
                break;
            case R.id.button6:
                mm.playStop();
                Log.i("PLAY", "STop" + i);
                break;

            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    finish();
    }
}
