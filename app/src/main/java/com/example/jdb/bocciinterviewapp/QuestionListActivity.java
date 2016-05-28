package com.example.jdb.bocciinterviewapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,TextToSpeech.OnInitListener{

    private ArrayList<String> questionArray;
    private BaseAdapter adapter;
    private TextToSpeech tts;
    private final String TAG="テキスト読み上げクラス　";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        questionArray=new ArrayList<String>();
        setQuestionArray();
        tts=new TextToSpeech(this,this);
        ListView questionList=(ListView)findViewById(R.id.questionList);
        adapter=new ListViewAdapter(this,R.layout.question_item,questionArray);
        questionList.setAdapter(adapter);
        questionList.setOnItemClickListener(this);
    }

    private class ViewHolder{
        TextView questionTextView;
        View view;
    }

    private class ListViewAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private int itemLayoutId;

        ListViewAdapter(Context context, int itemLayoutId, List<String> questionArray){
            super();
            this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayoutId=itemLayoutId;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            ViewHolder holder;
            //最初だけViewをinflateし、以降は再利用
            if(convertView==null){
                convertView=inflater.inflate(itemLayoutId,parent,false); //activity_mainの<ListView>にlist_itemsをinflateしてconvertVとする
                //ViewHolderを生成
                holder=new ViewHolder();
                holder.questionTextView=(TextView) convertView.findViewById(R.id.questionText);
                holder.view=(View)convertView.findViewById(R.id.questionItem);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag(); //holderを使って再利用
            }
            holder.questionTextView.setText(questionArray.get(pos));

            return convertView;
        }

        @Override
        public int getCount(){
            return questionArray.size();
        }

        @Override
        public Objects getItem(int pos){
            return null;
        }

        @Override
        public long getItemId(int pos){
            return 0;
        }
    }

    private void setQuestionArray(){
        //TODO データベースから質問引っ張ってきて
        questionArray.add("まず、自己紹介をしてください。");
        questionArray.add("では、あなたが当社を志望した理由を教えて下さい。");
        questionArray.add("では、あなたの長所を教えて下さい。");
    }

    @Override
    public void onItemClick(AdapterView<?> parent,View v,int pos,long id){
        //TODO 再生
        System.out.println(questionArray.get(pos));
        speechText(questionArray.get(pos));
    }

    @Override
    public void onInit(int status){
        if(TextToSpeech.SUCCESS==status){
            Log.d(TAG,"初期化");
        }else{
            Log.d(TAG,"初期化失敗");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void speechText(String speechStr){
        if(speechStr.length()>0){
            if(tts.isSpeaking()){
                tts.stop();
                return;
            }
            setSpeechRate(1.0f);
            setSpeechPitch(1.0f);
            //HashMap<String,String> map=new HashMap<String,String>();
            //map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"メッセージID");
            String utteranceId=this.hashCode()+"";
            tts.speak(speechStr,TextToSpeech.QUEUE_FLUSH,null,utteranceId);
            setTtsListener();
        }
    }

    private void setSpeechRate(float rate){
        if(tts!=null){
            tts.setSpeechRate(rate);
        }
    }

    private void setSpeechPitch(float pitch){
        if(tts!=null){
            tts.setPitch(pitch);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        shutDown();
    }

    private void shutDown(){
        if(tts!=null){
            tts.shutdown();
        }
    }

    private void setTtsListener(){
        if(Build.VERSION.SDK_INT>=15){
            int rs=tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.d(TAG,"progress on Start"+utteranceId);
                }

                @Override
                public void onDone(String utteranceId) {
                    Log.d(TAG,"progress on Done"+utteranceId);
                }

                @Override
                public void onError(String utteranceId) {
                    Log.d(TAG,"progress on Error"+utteranceId);
                }
            });
            if(TextToSpeech.SUCCESS!=rs){
                Log.e(TAG,"なんか失敗した");
            }
        }else{
            //SDK15以下はとりあえず未実装
        }
    }
}
