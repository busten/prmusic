package xyz.prmusic.servers.music;

import xyz.prmusic.entity.music.Music_album;
import xyz.prmusic.mapper.music.Music_albumMapper;

import java.util.ArrayList;

public interface Music_albumService extends Music_albumMapper {
    @Override
    void addAlbum(Music_album music_album);

    @Override
    void deleteAlbum(Integer[] albumid);

    @Override
    void updateAlbum(Music_album music_album);

    @Override
    Music_album findAlbumById(Integer albumid);

    @Override
    ArrayList<Music_album> getAllAlbum();

    @Override
    ArrayList<Music_album> getAlbumRecommend();

    @Override
    ArrayList<Music_album> getSelectedAlbum();
}
