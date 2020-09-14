package xyz.prmusic.servers.music;

import xyz.prmusic.entity.music.Music;
import xyz.prmusic.entity.music.Music_user_list;
import xyz.prmusic.mapper.music.MusicMapper;

import java.util.ArrayList;

public interface MusicService extends MusicMapper {
    @Override
    void addMusic(Music music) throws Exception;

    @Override
    void delete(Integer[] musicsid) throws Exception;

    @Override
    void update(Music music) throws Exception;

    @Override
    ArrayList<Music> findMusicByMusicId(Integer[] musicsid) throws Exception;

    @Override
    ArrayList<Music> getRandom();

    @Override
    ArrayList<Music> findMusicByAlbumId(Integer[] albumid) throws Exception;

    @Override
    ArrayList<Music> getNewMusic();

    @Override
    ArrayList<Music> getSongMusic(Integer song_id);

    @Override
    ArrayList<Music> getAllMusic();

    @Override
    ArrayList<Music_user_list> getUserMusicListByUid(Integer uid);

    @Override
    void addUserMusicList(Object[] obj) throws Exception;

    @Override
    ArrayList<Music> getMusicListMusic(Integer listid);

    @Override
    ArrayList<String> search(String obj);
}
