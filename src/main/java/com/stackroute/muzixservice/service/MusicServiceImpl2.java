package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MusicServiceImpl2 implements MusicService{


    @Override
    public Music saveTrack(Music music) {
        return null;
    }

    @Override
    public List<Music> displayAllTracks() {
        return null;
    }

    @Override
    public Music displayTrackById(int id) {
        return null;
    }

    @Override
    public Music updateTrack(Music music, int id) {
        return null;
    }

    @Override
    public void deleteTracks(int id) {

    }

    @Override
    public List<Music> getTrackByName(String trackName) {
        return null;
    }
}
