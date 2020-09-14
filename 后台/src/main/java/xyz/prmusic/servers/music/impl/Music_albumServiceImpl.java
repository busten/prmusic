package xyz.prmusic.servers.music.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.prmusic.entity.music.Music_album;
import xyz.prmusic.mapper.music.Music_albumMapper;
import xyz.prmusic.servers.music.Music_albumService;

import java.util.ArrayList;

@Service
public class Music_albumServiceImpl implements Music_albumService {
    @Autowired
    private Music_albumMapper music_albumMapper;
    @Override
    public void addAlbum(Music_album music_album) {

    }

    @Override
    public void deleteAlbum(Integer[] albumid) {

    }

    @Override
    public void updateAlbum(Music_album music_album) {

    }

    @Override
    public Music_album findAlbumById(Integer albumid) {
        if (albumid == null || albumid == 0){
            return null;
        }
        return music_albumMapper.findAlbumById(albumid);
    }

    @Override
    public ArrayList<Music_album> getAllAlbum() {
        return music_albumMapper.getAllAlbum();
    }

    @Override
    public ArrayList<Music_album> getAlbumRecommend() {
        return music_albumMapper.getAlbumRecommend();
    }

    @Override
    public ArrayList<Music_album> getSelectedAlbum() {
        return music_albumMapper.getSelectedAlbum();
    }
}
