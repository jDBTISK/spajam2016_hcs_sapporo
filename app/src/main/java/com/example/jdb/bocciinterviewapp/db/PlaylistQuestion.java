package com.example.jdb.bocciinterviewapp.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by muna1 on 2016/05/28.
 */
public class PlaylistQuestion extends RealmObject {
    private int playlistId;
    private String playlistName;
    private RealmList<InterviewQuestion> interviewQuestions;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public RealmList<InterviewQuestion> getInterviewQuestions() {
        return interviewQuestions;
    }

    public void setInterviewQuestions(RealmList<InterviewQuestion> interviewQuestions) {
        this.interviewQuestions = interviewQuestions;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
