package com.example.jdb.bocciinterviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class Gesture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        initTabs();
    }
    protected void initTabs() {
        try {
            TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
            tabHost.setup();
            TabHost.TabSpec spec;

            // Tab1
            spec = tabHost.newTabSpec("Tab1")
                    .setIndicator("入室")
                    .setContent(R.id.linearLayout);
            tabHost.addTab(spec);

            // Tab2
            spec = tabHost.newTabSpec("Tab2")
                    .setIndicator("面接中")
                    .setContent(R.id.linearLayout2);
            tabHost.addTab(spec);

            // Tab3
            spec = tabHost.newTabSpec("Tab3")
                    .setIndicator("退出")
                    .setContent(R.id.linearLayout3);
            tabHost.addTab(spec);

            tabHost.setCurrentTab(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
