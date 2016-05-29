package com.example.jdb.bocciinterviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jdb.bocciinterviewapp.db.InterviewQuestion;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        Realm realm=Realm.getInstance(this);
        RealmQuery<InterviewQuestion> query=realm.where(InterviewQuestion.class);
        RealmResults<InterviewQuestion> rs=query.findAll();
        if(rs.size()==0){
            String[] questions={"自己紹介をお願いします。","あなたの長所を教えて下さい","あなたの短所を教えて下さい。",
                    "学生時代で最も頑張ったことを教えて下さい。","アルバイトの内容を教えて下さい。","リーダーシップを取った経験はありますか？",
                    "周りの方のあなたへの評価を教えて下さい。","学生時代に学んだことはなんですか。","あなたの人生における成功体験を教えて下さい。",
                    "あなたの人生における失敗体験を教えて下さい。","これまでの人生で一番感動したことを教えて下さい。",
                    "今までで一番うれしかったことはなんですか。","今までで一番悔しかったことはなんですか。",
                    "あなたが今通っている学校に入学した理由を教えて下さい。","趣味を教えて下さい。","あなたの夢を教えて下さい。",
                    "5年後、10年後の自分は何をしていますか。","座右の銘はなんですか。","あなたにとって仕事とはなんですか。",
                    "あなたが入社することによって当社にどのようなメリットがありますか。"};
            realm.beginTransaction();
            for(int i=1; i<=20; i++){
                InterviewQuestion q=realm.createObject(InterviewQuestion.class);
                q.setId(i);
                q.setQuestion(questions[i-1]);
            }
            realm.commitTransaction();
        }else{
            System.out.println("はい");
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button:
                intent = new Intent(this, Gesture.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, Gesture.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(this, Question_and_answer.class );
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(this, Gesture.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
