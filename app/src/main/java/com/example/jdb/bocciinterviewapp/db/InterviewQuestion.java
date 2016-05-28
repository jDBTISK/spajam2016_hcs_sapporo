package com.example.jdb.bocciinterviewapp.db;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jDB on 16/05/28.
 */
public class InterviewQuestion extends RealmObject implements Serializable{
    @PrimaryKey
    private int id;
    private String question;
    private String answer;

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public void setQuestion(String question){
        this.question=question;
    }

    public String getQuestion(){
        return question;
    }

    public void setAnswer(String answer){
        this.answer=answer;
    }

    public String getAnswer(){
        return answer;
    }
}
