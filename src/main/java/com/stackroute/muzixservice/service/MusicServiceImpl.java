package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Primary
@CacheConfig(cacheNames = "music")
public class MusicServiceImpl implements MusicService {
    MusicRepository musicRepository;

    public void simulateDelay(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository){
        this.musicRepository=musicRepository;
    }

    @Override
    public Music saveTrack(Music music) {
        Music saveTracks=musicRepository.save(music);
        return saveTracks;
    }

    @Cacheable
    @Override
    public List<Music> displayAllTracks() {
        simulateDelay();
        List<Music> musicList=(List<Music>)musicRepository.findAll();
        return musicList;
    }

    @Override
    public Music displayTrackById(int id) {
        return null;
    }

    @CachePut
    @Override
    public void deleteTracks(int id) {
        musicRepository.deleteById(id);

    }

    @CachePut
    @Override
    public Music updateTrack(Music music, int id) {
        Music music1=musicRepository.findById(id).get();
        music1.setTrackComments(music.getTrackComments());
        return music1;
        //return (List<Music>) musicRepository.save(music1);
    }

    @Override
    public List<Music> getTrackByName(String trackName){

        List<Music> lists = null;
        lists = musicRepository.getTrackByName(trackName);
        if (lists.equals(null))
        {
            try {
                throw new TrackNotFoundException("Track not found exception");
            } catch (TrackNotFoundException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    @PostConstruct
    public  void  loadData(){
        musicRepository.save(Music.builder().id(6).trackName("mere sang to chal zara").trackComments("new song").build());
        musicRepository.save(Music.builder().id(7).trackName("yaariyan").trackComments("another song").build());
        musicRepository.save(Music.builder().id(8).trackName("aami je tomaar").trackComments("monjo's song").build());
    }
}
