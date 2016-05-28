package com.example.jdb.bocciinterviewapp;

import com.example.jdb.bocciinterviewapp.db.InterviewQuestion;
import com.example.jdb.bocciinterviewapp.db.PlayList;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PlayListOperation {

    Realm realm;
    InterviewQuestion interviewQuestion;
    ArrayList<String> nameList;

    public PlayListOperation(Realm realm) {
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
//        Realm.deleteRealm(realmConfig);
//        realm = Realm.getInstance(realmConfig);
        this.realm = realm;
    }

    public int getMaxId() {
        Number maxId = realm.where(InterviewQuestion.class).max("id");
        int nextId = 1;
        if(maxId != null) nextId = maxId.intValue() + 1;
        return nextId;
    }
    public void addItemList(int id, String question, String answer) {
        realm.beginTransaction();
        InterviewQuestion interviewQuestion = realm.createObject(InterviewQuestion.class);
        interviewQuestion.setId(id);
        interviewQuestion.setQuestion(question);
        interviewQuestion.setAnswer(answer);
        realm.commitTransaction();
    }
    public void removeItemList(int id) {
        RealmResults<InterviewQuestion> results = realm.where(InterviewQuestion.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        results.clear();
        realm.commitTransaction();
    }

    public int getMaxPlayListId() {
        Number maxId = realm.where(PlayList.class).max("id");
        int nextId = 1;
        if(maxId != null) nextId = maxId.intValue() + 1;
        return nextId;
    }

    public InterviewQuestion getPlayListItem(int id) {
        RealmResults<PlayList> results = realm.where(PlayList.class).equalTo("id", id).findAll();
        InterviewQuestion interviewQuestion = null;
        if(results.size() > 0) {
            interviewQuestion = results.get(0).getInterviewQuestion();
        }

        return interviewQuestion;
    }

    public ArrayList<String> getNameList() {
        nameList = new ArrayList<String>();
        RealmResults<PlayList> results = realm.where(PlayList.class).findAll();
        for(int index = 0; index < results.size(); index++) {
            nameList.add(results.get(index).getName());
        }
        return nameList;
    }

    public void addPlayList(int id, String name, InterviewQuestion interviewQuestion) {
        realm.beginTransaction();
        PlayList playList = realm.createObject(PlayList.class);
        playList.setId(id);
        playList.setName(name);
        playList.setInterviewQuestion(interviewQuestion);

    }

    public void removePlayList(int id) {
        RealmResults<PlayList> results = realm.where(PlayList.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        results.clear();
        realm.commitTransaction();

    }

}
