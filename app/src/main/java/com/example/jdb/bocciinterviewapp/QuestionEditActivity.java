package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jdb.bocciinterviewapp.db.InterviewQuestion;

import io.realm.Realm;
import io.realm.RealmResults;

public class QuestionEditActivity extends Activity {

    private EditText questionEdit,answerEdit;
    private boolean newFlag=false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_edit);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        String question=intent.getStringExtra("question");
        String answer=intent.getStringExtra("answer");
        questionEdit=(EditText)findViewById(R.id.questionEdit);
        answerEdit=(EditText)findViewById(R.id.answerEdit);
        if(id>=0){
            newFlag=true;
        }else{
            questionEdit.setText(question);
            answerEdit.setText(answer);
        }
    }

    public void savedEditor(View v){
        Realm realm=Realm.getInstance(this);
        realm.beginTransaction();
        if(newFlag){
            Number maxId=realm.where(InterviewQuestion.class).max("id");
            int nextId=1;
            if(maxId!=null)nextId+=maxId.intValue();
            InterviewQuestion insertQ=realm.createObject(InterviewQuestion.class);
            insertQ.setId(nextId);
            insertQ.setQuestion(questionEdit.getText().toString());
            insertQ.setAnswer(answerEdit.getText().toString());
        }else{
            RealmResults<InterviewQuestion> rs=realm.where(InterviewQuestion.class).equalTo("id",id).findAll();
            InterviewQuestion q=rs.first();
            realm.beginTransaction();
            q.setQuestion(questionEdit.getText().toString());
            q.setAnswer(answerEdit.getText().toString());
            realm.commitTransaction();
        }
        realm.commitTransaction();
        Toast.makeText(this,"保存しました。",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelEditor(View v){
        finish();
    }
}
