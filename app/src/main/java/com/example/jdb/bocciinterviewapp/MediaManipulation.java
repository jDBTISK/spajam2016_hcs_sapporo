package com.example.jdb.bocciinterviewapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;

public class MediaManipulation {

    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String filePath;
    private File dir, file;

    public MediaManipulation() {
        mediaPlayer = new MediaPlayer();
        mediaRecorder = new MediaRecorder();

        dir = new File(Environment.getExternalStorageDirectory(), "BocciMedia");    //保存ディレクトリの指定
        if(dir.exists() == false) dir.mkdir();  //保存ディレクトリがない場合作成
    }

    public void recordingStart(int number) {
        filePath = "bocci" + number + ".3gp";
        file = new File(dir, filePath);

        //設定
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(file.getPath());

        //準備
        try {
            mediaRecorder.prepare();
        } catch(Exception e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    public void recordingStop() {
        try {
            mediaRecorder.stop();
            mediaRecorder.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        mediaRecorder.release();
        mediaPlayer.release();
    }

    public void playStart(int number) {
        filePath = "bocci" + number + ".3gp";
        file = new File(dir, filePath);

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch(Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }

    public void playStop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

}
