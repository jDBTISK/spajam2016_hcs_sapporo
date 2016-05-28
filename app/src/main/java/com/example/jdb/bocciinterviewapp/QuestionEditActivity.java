package com.example.jdb.bocciinterviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jdb.bocciinterviewapp.db.InterviewQuestion;

import io.realm.Realm;

public class QuestionEditActivity extends Activity {

    private EditText questionEdit,answerEdit;
    private InterviewQuestion q;
    private boolean newFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_edit);
        Intent intent=getIntent();
        q=(InterviewQuestion)intent.getSerializableExtra("q");
        questionEdit=(EditText)findViewById(R.id.questionEdit);
        answerEdit=(EditText)findViewById(R.id.answerEdit);
        if(q==null){
            newFlag=true;
        }else{
            questionEdit.setText(q.getQuestion());
            answerEdit.setText(q.getAnswer());
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
            q.setQuestion(questionEdit.getText().toString());
            q.setAnswer(answerEdit.getText().toString());
        }
        realm.commitTransaction();
        Toast.makeText(this,"保存しました。",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelEditor(View v){
        finish();
    }
}
