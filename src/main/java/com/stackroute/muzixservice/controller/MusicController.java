package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value ="/api/v1")
public class MusicController {

    private MusicService musicService;
    @Autowired
    public MusicController(MusicService musicService) {

        this.musicService = musicService;
    }
    @PostMapping("/music")
    public ResponseEntity<?> saveTrack(@RequestBody Music music) throws TrackAlreadyExistsException {
        RequestEntity requestEntity;
        //    try {
        musicService.saveTrack(music);
        return new ResponseEntity<String>("successfully created",HttpStatus.OK);

//         }catch (TrackAlreadyExistsException ex)
//         {
//             return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//
//         }

    }
    @GetMapping("/music")
    public ResponseEntity<?> displayAllTracks()
    {
        List<Music> musicList=musicService.displayAllTracks();
        return new ResponseEntity<List<Music>>(musicList,HttpStatus.OK);
    }
    @GetMapping("/tracks/{id}")
    public ResponseEntity<?> displayTracksById(@PathVariable int id)
    {
       Music  musicList=musicService.displayTrackById(id);
        return new ResponseEntity<Music>(musicList,HttpStatus.OK);
    }

    @DeleteMapping("/music/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id)
    {
        musicService.deleteTracks(id);
        return new ResponseEntity<List<Music>>(musicService.displayAllTracks(),HttpStatus.OK);
    }

    @PutMapping("/music/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Music music,@PathVariable int id) throws TrackNotFoundException {
        //  try{
        Music music1= (Music) musicService.updateTrack(music,id);
        return new ResponseEntity<Music>(music1,HttpStatus.OK);}
//        catch (TrackNotFoundException ex)
//        {
//            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }

}

