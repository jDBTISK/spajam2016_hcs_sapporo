package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.util.ArrayList;

public class Recording extends Activity implements View.OnClickListener {
    String rename;
    Button recordingStart, recordingStop, saiseiStart, saiseiStop;
    MediaManipulation mm = new MediaManipulation();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    boolean flug=true;
    TextView textView;
    int i=0;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        recordingStart = (Button) findViewById(R.id.recordingstart);
        recordingStop = (Button) findViewById(R.id.recordingstop);
        Intent intent=getIntent();
        String situmon ="situmon";
        list=intent.getStringArrayListExtra(situmon);
        textView=(TextView)findViewById(R.id.textView11);

        textView.setText(list.get(i));
        i++;

        recordingStart.setOnClickListener(this);
        recordingStop.setOnClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.recordingstart:

                if (flug) {
                    mm.recordingStart();


                    recordingStart.setText("停止");
                    recordingStart.setTextColor(Color.WHITE);
                    Log.i("RECORDING", "START");
                    flug=false;
                }else {
                    mm.recordingStop();
                    recordingStart.setText("録音");
                    recordingStart.setTextColor(Color.RED);
                    Log.i("RECORDING", "STop");
                    final EditText editView = new EditText(Recording.this);
                    new AlertDialog.Builder(Recording.this)
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle("ファイル名指定")
                            //setViewにてビューを設定します。
                            .setView(editView)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    rename=editView.getText().toString();
                                    File newfile=new File(Environment.getExternalStorageDirectory(),"BocciMedia/"+rename+".3gp");
                                    File file = new File(Environment.getExternalStorageDirectory(), "BocciMedia/bocci.3gp");
                                    if (file.exists()) {
                                        //ファイル名変更実行
                                        file.renameTo(newfile);
                                        System.out.println("ファイル名を変更しました。");

                                    } else {
                                        System.out.println("ファイルが存在しません。");
                                    }

                                }
                            })
                            .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .show();
                    flug=true;

                }
                break;
            case R.id.recordingstop:
                String test=list.get(i);
                if (test==null){
                    textView.setText("終了です");
                    recordingStop.isEnabled();
                }else {
                    textView.setText(test);
                }



                break;


            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mm.close();
        finish();
    }


}