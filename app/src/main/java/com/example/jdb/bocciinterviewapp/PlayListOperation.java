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
        Number id = realm.where(PlayList.class).max("id");
        if(id == null) {
            RealmResults<InterviewQuestion> results = realm.where(InterviewQuestion.class).lessThan("id", 3).findAll();
            realm.beginTransaction();
            InterviewQuestion inputInterview = null;
            for(int i = 0; i < results.size(); i++) {
                inputInterview = realm.createObject(InterviewQuestion.class);
                inputInterview.setId(results.get(i).getId());
                inputInterview.setQuestion(results.get(i).getQuestion());
                inputInterview.setAnswer(results.get(i).getAnswer());
            }
            realm.commitTransaction();
            realm.beginTransaction();
            PlayList playList = realm.createObject(PlayList.class);
            playList.setId(1);
            playList.setName("PlayList1");
            playList.setInterviewQuestion(inputInterview);
            realm.commitTransaction();

            results = realm.where(InterviewQuestion.class).greaterThan("id", 5).findAll();
            realm.beginTransaction();
            inputInterview = null;
            for(int i = 0; i < results.size(); i++) {
                inputInterview = realm.createObject(InterviewQuestion.class);
                inputInterview.setId(results.get(i).getId());
                inputInterview.setQuestion(results.get(i).getQuestion());
                inputInterview.setAnswer(results.get(i).getAnswer());
            }
            realm.commitTransaction();
            realm.beginTransaction();
            playList = realm.createObject(PlayList.class);
            playList.setId(2);
            playList.setName("PlayList2");
            playList.setInterviewQuestion(inputInterview);
            realm.commitTransaction();
        }
    }

    public int getMaxId() {
        Number maxId = realm.where(InterviewQuestion.class).max("id");
        int nextId = 1;
        if(maxId != null) nextId = maxId.intValue() + 1;
        return nextId;
    }

//    public InterviewQuestion getInterviewQuestion(int id) {
//        InterviewQuestion interviewQuestion;
//
//        return interviewQuestion;
//    }

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

    public ArrayList<String[]> getPlayListItem(String name) {
        RealmResults<PlayList> results = realm.where(PlayList.class).equalTo("name", name).findAll();
        InterviewQuestion interviewQuestion = null;
        ArrayList<String[]> array = null;
        if(results.size() > 0) {
            interviewQuestion = results.get(0).getInterviewQuestion();
            RealmResults<InterviewQuestion> results1 = realm.where(InterviewQuestion.class).findAll();
            array = new ArrayList<String[]>();
            String[] qAndA = new String[2];
            for(int i = 0; i < results1.size(); i++) {
                qAndA[0] = results1.get(i).getQuestion();
                qAndA[1] = results1.get(i).getAnswer();
                array.add(qAndA);
            }
        }
        return array;
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
