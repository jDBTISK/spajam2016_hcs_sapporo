package com.example.jdb.bocciinterviewapp.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by muna1 on 2016/05/29.
 */
public class PlayList extends RealmObject {
    @PrimaryKey
    private int playlistId;
    private String playlistName;
    private RealmList<InterviewQuestion> iqList;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public RealmList<InterviewQuestion> getIqList() {
        return iqList;
    }

    public void setIqList(RealmList<InterviewQuestion> iqList) {
        this.iqList = iqList;
    }
}
