package com.example.jdb.bocciinterviewapp;

import com.example.jdb.bocciinterviewapp.db.PlayList;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by muna1 on 2016/05/29.
 */
public class PlaylistOperation {
    Realm realm;
    ArrayList<String> nameList;
    ArrayList<PlayList> playList;

//    public PlaylistOperation() {
//
//    }
    public PlaylistOperation(Realm realm) {
        this.realm = realm;
    }

    public ArrayList<String> getNameList() {
        nameList = new ArrayList<String>();
        RealmQuery<PlayList> query = realm.where(PlayList.class);
        RealmResults<PlayList> result = query.findAll();
        PlayList playList;

        for(int index = 0; index < result.size(); index++) {
            playList = result.get(index);
            nameList.add(playList.getPlaylistName());
        }

        return nameList;
    }

    public ArrayList<PlayList> getPlayList() {
        playList = new ArrayList<PlayList>();
        RealmQuery<PlayList> query = realm.where(PlayList.class);


        return playList;
    }

}
