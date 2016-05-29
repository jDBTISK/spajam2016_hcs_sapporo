package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Ryosuke on 2016/05/29.
 */
public class TextToSpeech extends Activity implements android.speech.tts.TextToSpeech.OnInitListener{
    private android.speech.tts.TextToSpeech tts;
    private static final String TAG = "TestTTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TTS インスタンス生成
        tts = new android.speech.tts.TextToSpeech(this, this);

    }


    @Override
    public void onInit(int status) {
        // TTS初期化
        if (android.speech.tts.TextToSpeech.SUCCESS == status) {
            Log.d(TAG, "initialized");
        } else {
            Log.e(TAG, "faile to initialize");
        }
    }

    public void onTextToSpeech(String s) {
        speechText(s);
    }

    private void shutDown(){
        if (null != tts) {
            // to release the resource of TextToSpeech
            tts.shutdown();
        }
    }

    private void speechText(String string) {
        // EditTextからテキストを取得

        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                tts.stop();
                return;
            }
            setSpeechRate(1.0f);
            setSpeechPitch(1.0f);

            // tts.speak(text, TextToSpeech.QUEUE_FLUSH, null) に
            // KEY_PARAM_UTTERANCE_ID を HasMap で設定
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(android.speech.tts.TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"messageID");

            tts.speak(string, android.speech.tts.TextToSpeech.QUEUE_FLUSH, map);
            setTtsListener();

        }
    }

    // 読み上げのスピード
    private void setSpeechRate(float rate){
        if (null != tts) {
            tts.setSpeechRate(rate);
        }
    }

    // 読み上げのピッチ
    private void setSpeechPitch(float pitch){
        if (null != tts) {
            tts.setPitch(pitch);
        }
    }

    // 読み上げの始まりと終わりを取得
    private void setTtsListener(){
        // android version more than 15th
        if (Build.VERSION.SDK_INT >= 15)
        {
            int listenerResult = tts.setOnUtteranceProgressListener(new UtteranceProgressListener()
            {
                @Override
                public void onDone(String utteranceId)
                {
                    Log.d(TAG,"progress on Done " + utteranceId);
                }

                @Override
                public void onError(String utteranceId)
                {
                    Log.d(TAG,"progress on Error " + utteranceId);
                }

                @Override
                public void onStart(String utteranceId)
                {
                    Log.d(TAG,"progress on Start " + utteranceId);
                }

            });
            if (listenerResult != android.speech.tts.TextToSpeech.SUCCESS)
            {
                Log.e(TAG, "failed to add utterance progress listener");
            }
        }
        else
        {
            // less than 15th
            int listenerResult = tts.setOnUtteranceCompletedListener(new android.speech.tts.TextToSpeech.OnUtteranceCompletedListener()
            {
                @Override
                public void onUtteranceCompleted(String utteranceId)
                {
                    Log.d(TAG,"progress on Completed " + utteranceId);
                }
            });

            if (listenerResult != android.speech.tts.TextToSpeech.SUCCESS)
            {
                Log.e(TAG, "failed to add utterance completed listener");
            }
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        shutDown();
    }

}
