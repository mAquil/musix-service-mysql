package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;

import java.util.List;

public interface MusicService {
    public Music saveTrack(Music music);
    public List<Music> displayAllTracks();
    public Music displayTrackById(int id);
    public Music updateTrack(Music music, int id);
    public void deleteTracks(int id);
    public  List<Music> getTrackByName(String trackName);

}



