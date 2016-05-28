package com.example.jdb.bocciinterviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class QuestionListActivity extends AppCompatActivity {

    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        ListView questionList=(ListView)findViewById(R.id.questionList);
        //とりあえず
    }
}
