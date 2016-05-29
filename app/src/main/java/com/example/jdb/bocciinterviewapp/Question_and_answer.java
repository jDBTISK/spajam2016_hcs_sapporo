package com.example.jdb.bocciinterviewapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Question_and_answer extends TabActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answer);
        TabHost tabHost = getTabHost();
        TabSpec tab1 = tabHost.newTabSpec("質問リスト");
        tab1.setIndicator("tab1");
        tab1.setContent(new Intent(this, QuestionListActivity.class));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("プレイリスト");
        tab2.setIndicator("tab2");
        tab2.setContent(new Intent(this, Voice_reproducing.class));
        tabHost.addTab(tab2);
    }
}
