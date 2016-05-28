package com.example.jdb.bocciinterviewapp.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by muna1 on 2016/05/29.
 */
public class PlayList extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private InterviewQuestion interviewQuestion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InterviewQuestion getInterviewQuestions() {
        return interviewQuestion;
    }

    public void setInterviewQuestion(InterviewQuestion interviewQuestion) {
        this.interviewQuestion = interviewQuestion;
    }
}
