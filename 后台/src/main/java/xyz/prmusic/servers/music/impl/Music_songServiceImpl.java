package xyz.prmusic.servers.music.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.prmusic.entity.music.Music_song;
import xyz.prmusic.mapper.music.Music_songMapper;
import xyz.prmusic.servers.music.Music_songService;

import java.util.ArrayList;

@Service
public class Music_songServiceImpl implements Music_songService {
    @Autowired
    private Music_songMapper music_songMapper;
    @Override
    public ArrayList<Music_song> getAllSong() {
        return music_songMapper.getAllSong();
    }
}
