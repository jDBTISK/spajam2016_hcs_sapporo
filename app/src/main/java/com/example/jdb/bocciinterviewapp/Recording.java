package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class Recording extends Activity implements View.OnClickListener {
    String rename;
    Button recordingStart, recordingStop, saiseiStart, saiseiStop;
    MediaManipulation mm = new MediaManipulation();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        recordingStart = (Button) findViewById(R.id.recordingstart);
        recordingStop = (Button) findViewById(R.id.recordingstop);
        saiseiStart = (Button) findViewById(R.id.button2);
        saiseiStop = (Button) findViewById(R.id.button3);

        recordingStart.setOnClickListener(this);
        recordingStop.setOnClickListener(this);
        saiseiStart.setOnClickListener(this);
        saiseiStop.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.recordingstart:
                mm.recordingStart();

                recordingStart.setText("録音中");
                recordingStart.setTextColor(Color.RED);
                Log.i("RECORDING", "START");
                break;
            case R.id.recordingstop:
                mm.recordingStop();
                recordingStart.setText("録音");
                recordingStart.setTextColor(Color.WHITE);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Recording Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jdb.bocciinterviewapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Recording Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jdb.bocciinterviewapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}