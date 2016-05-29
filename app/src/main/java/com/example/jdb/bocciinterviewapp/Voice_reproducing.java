package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Voice_reproducing extends Activity {
    private File[] files;
    private List<String> songList = new ArrayList<String>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_reproducing);
        String sdPath = Environment.getExternalStorageDirectory().getPath()+"/BocciMedia";
        files = new File(sdPath).listFiles();
        for(int i = 0; i < files.length; i++){
            if(files[i].isFile() && files[i].getName().endsWith(".3gp")){
                songList.add(files[i].getName());
            }
        }
        lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, songList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);
                Intent intent=new Intent(getApplicationContext(),Player.class);
                intent.putExtra("pass",item);
                startActivity(intent);
            }
        });
    }

        public void showItem(String str){
        Toast.makeText(this, "ファイル名:" + str, Toast.LENGTH_SHORT).show();
        }
}

