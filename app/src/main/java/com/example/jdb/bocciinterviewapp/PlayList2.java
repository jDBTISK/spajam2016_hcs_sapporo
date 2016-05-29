package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PlayList2 extends Activity {
    private List<String> questionList = new ArrayList<String>();
    private ListView lv;
    ArrayList<String> list1, list2,interview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        //final PlayListOperation plo = new PlayListOperation(Realm.getInstance(this));
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add(0,"PlayList1");
        nameList.add(1,"PlayList2");
        ArrayList<String> list1=new ArrayList<>();
        ArrayList<String> list2=new ArrayList<>();

        list1.add(0,"自己紹介をお願いします");
        list1.add(1,"あなたの長所、短所を教えてください");
        list1.add(2,"志望動機を教えてください");

        list2.add(0,"あなたを家電製品に例えると何ですか？その理由は？");
        list2.add(1,"ザリガニとエビの違いを１０個あげて下さい");
        lv = (ListView) findViewById(R.id.listView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, nameList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);
                interview=sentaku(item);
                Intent intent=new Intent(getApplicationContext(),Recording.class);
                intent.putExtra("pass",item);
                intent.putStringArrayListExtra("situmon",interview);
                startActivity(intent);
            }
        });




    }
    public ArrayList<String> sentaku(String name){
        ArrayList<String> list = null;

        switch (name){
            case "PlayList1":
                list= list1;
            break;
            case  "PlayList2":
                list= list2;
            break;
            default:

                break;

        }
        return list;
    }
}
