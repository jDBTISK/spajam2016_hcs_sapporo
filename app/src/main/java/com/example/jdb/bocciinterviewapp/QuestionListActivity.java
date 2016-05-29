package com.example.jdb.bocciinterviewapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jdb.bocciinterviewapp.db.InterviewQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class QuestionListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,TextToSpeech.OnInitListener {

    private ArrayList<InterviewQuestion> questionArray;
    private BaseAdapter adapter;
    private TextToSpeech tts;
    private final String TAG = "テキスト読み上げクラス　";
    private int currentPosition = -1;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(realmConfig);
        realm = Realm.getInstance(realmConfig);
        realm=Realm.getInstance(this);
        questionArray = new ArrayList<InterviewQuestion>();
        setQuestionArray();
        tts = new TextToSpeech(this, this);
        ListView questionList = (ListView) findViewById(R.id.questionList);
        adapter = new ListViewAdapter(this, R.layout.question_item, questionArray);
        questionList.setAdapter(adapter);
        questionList.setOnItemClickListener(this);
        questionList.setOnItemLongClickListener(this);
    }

    public void addQuestionItem(View v) {
        Intent intent=new Intent(this,QuestionEditActivity.class);
        startActivity(intent);
    }

    private class ViewHolder {
        TextView questionTextView;
        View view;
    }

    private class ListViewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int itemLayoutId;

        ListViewAdapter(Context context, int itemLayoutId, List<InterviewQuestion> questionArray) {
            super();
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayoutId = itemLayoutId;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            ViewHolder holder;
            //最初だけViewをinflateし、以降は再利用
            if (convertView == null) {
                convertView = inflater.inflate(itemLayoutId, parent, false); //activity_mainの<ListView>にlist_itemsをinflateしてconvertVとする
                //ViewHolderを生成
                holder = new ViewHolder();
                holder.questionTextView = (TextView) convertView.findViewById(R.id.questionText);
                holder.view = (View) convertView.findViewById(R.id.questionItem);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag(); //holderを使って再利用
            }
            holder.questionTextView.setText(questionArray.get(pos).getQuestion());

            return convertView;
        }

        @Override
        public int getCount() {
            return questionArray.size();
        }

        @Override
        public Objects getItem(int pos) {
            return null;
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }
    }

    private void setQuestionArray() {
        //TODO データベースから質問引っ張ってきて
        RealmQuery<InterviewQuestion> query=realm.where(InterviewQuestion.class);
        RealmResults<InterviewQuestion> rs=query.findAll();
        for(int i=0; i<rs.size(); i++){
            InterviewQuestion q=rs.get(i);
            questionArray.add(q);
        }
        //TODO ↑あってるかな？

        /*InterviewQuestion q1=new InterviewQuestion();
        q1.setId(1);
        q1.setQuestion("まず、自己紹介をしてください。");
        questionArray.add(q1);
        InterviewQuestion q2=new InterviewQuestion();
        q1.setId(2);
        q1.setQuestion("では、あなたが弊社を志望した理由を教えて下さい。");
        questionArray.add(q2);
        InterviewQuestion q3=new InterviewQuestion();
        q1.setId(3);
        q1.setQuestion("では、あなたの長所を教えて下さい。");
        questionArray.add(q3);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        //TODO 再生
        speechText(questionArray.get(pos).getQuestion());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int pos, long id) {
        currentPosition = pos;
        alertCheck(questionArray.get(pos).getQuestion());
        return false;
    }

    @Override
    public void onInit(int status) {
        System.out.println(status);
        if (TextToSpeech.SUCCESS == status) {
            Log.d(TAG, "初期化");
        } else {
            Log.d(TAG, "初期化失敗");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void speechText(String speechStr) {
        if (speechStr.length() > 0) {
            if (tts.isSpeaking()) {
                tts.stop();
                return;
            }
            setSpeechRate(1.0f);
            setSpeechPitch(1.0f);
            //HashMap<String,String> map=new HashMap<String,String>();
            //map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"メッセージID");
            String utteranceId = this.hashCode() + "";
            tts.speak(speechStr, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
            setTtsListener();
        }
    }

    private void setSpeechRate(float rate) {
        if (tts != null) {
            tts.setSpeechRate(rate);
        }
    }

    private void setSpeechPitch(float pitch) {
        if (tts != null) {
            tts.setPitch(pitch);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shutDown();
    }

    private void shutDown() {
        if (tts != null) {
            tts.shutdown();
        }
    }

    private void setTtsListener() {
        if (Build.VERSION.SDK_INT >= 15) {
            int rs = tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.d(TAG, "progress on Start" + utteranceId);
                }

                @Override
                public void onDone(String utteranceId) {
                    Log.d(TAG, "progress on Done" + utteranceId);
                }

                @Override
                public void onError(String utteranceId) {
                    Log.d(TAG, "progress on Error" + utteranceId);
                }
            });
            if (TextToSpeech.SUCCESS != rs) {
                Log.e(TAG, "なんか失敗した");
            }
        } else {
            //SDK15以下はとりあえず未実装
        }
    }

    private void alertCheck(String item){
        String[] menu={"編集","削除"};
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setItems(menu,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int idx){
                if(idx==0){
                    //TODO 編集
                    startEditActivity();
                }else if(idx==1){
                    deleteCheck();
                }
            }
        });
        alert.show();
    }

    private void startEditActivity(){
        int id=questionArray.get(currentPosition).getId();
        RealmResults<InterviewQuestion> rs=realm.where(InterviewQuestion.class).equalTo("id",id).findAll();
        Intent intent=new Intent(this,QuestionEditActivity.class);
        System.out.println(rs.first().getId()+rs.first().getQuestion());
        InterviewQuestion q=rs.first();
        intent.putExtra("id",q.getId());
        intent.putExtra("question",q.getQuestion());
        intent.putExtra("answer",q.getAnswer());
        startActivity(intent);
    }

    private void deleteCheck(){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("削除");
        alertDialogBuilder.setMessage("本当に削除しますか？");
        alertDialogBuilder.setNeutralButton("いいえ",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialogBuilder.setPositiveButton("はい",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        deleteItem();
                    }
                });
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }

    private void deleteItem(){
        int id=questionArray.get(currentPosition).getId();
        RealmResults<InterviewQuestion> rs=realm.where(InterviewQuestion.class).equalTo("id",id).findAll();
        realm.beginTransaction();
        rs.remove(0);
        realm.commitTransaction();
        questionArray.remove(currentPosition);
        adapter.notifyDataSetChanged();
    }
}
