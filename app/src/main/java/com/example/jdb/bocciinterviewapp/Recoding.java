package com.example.jdb.bocciinterviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Recoding extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button:
                intent = new Intent(this, Recording.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, Gesture.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(this, Question_and_answer.class );
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(this, Gesture.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
