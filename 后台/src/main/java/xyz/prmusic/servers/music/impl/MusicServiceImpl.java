package xyz.prmusic.servers.music.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.prmusic.entity.music.Music;
import xyz.prmusic.entity.music.Music_user_list;
import xyz.prmusic.mapper.music.MusicMapper;
import xyz.prmusic.servers.music.MusicService;

import java.util.ArrayList;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;
    @Override
    public void addMusic(Music music) throws Exception {
        if (music == null){
            throw new Exception("音乐信息不能为空");
        }
        musicMapper.addMusic(music);
    }

    @Override
    public void delete(Integer[] musicsid) throws Exception {
        if (musicsid == null || musicsid.length == 0){
            throw new Exception("音乐标识id不能为空");
        }
        musicMapper.delete(musicsid);
    }

    @Override
    public void update(Music music) throws Exception {
        if (music == null){
            throw new Exception("音乐信息不能为空");
        }
        musicMapper.update(music);
    }

    @Override
    public ArrayList<Music> findMusicByMusicId(Integer[] musicsid) throws Exception {
        if (musicsid == null || musicsid.length == 0){
            throw new Exception("音乐标识id不能为空");
        }
        return musicMapper.findMusicByMusicId(musicsid);
    }

    @Override
    public ArrayList<Music> getRandom() {
        return musicMapper.getRandom();
    }

    @Override
    public ArrayList<Music> findMusicByAlbumId(Integer[] albumid) throws Exception {
        if (albumid.length == 0 || albumid == null){
            return null;
        }
        return musicMapper.findMusicByAlbumId(albumid);
    }

    @Override
    public ArrayList<Music> getNewMusic() {
        return musicMapper.getNewMusic();
    }

    @Override
    public ArrayList<Music> getSongMusic(Integer song_id) {
        if (song_id != null){
            return musicMapper.getSongMusic(song_id);
        }
        return null;
    }

    @Override
    public ArrayList<Music> getAllMusic() {
        return musicMapper.getAllMusic();
    }

    @Override
    public ArrayList<Music_user_list> getUserMusicListByUid(Integer uid) {
        return musicMapper.getUserMusicListByUid(uid);
    }

    @Override
    public void addUserMusicList(Object[] obj) throws Exception {
        if (obj == null){
            throw new Exception("列表信息不能为空");
        }
        if (obj[0] == null || obj[0].equals("")){
            throw new Exception("不能缺少列表名称");
        }
        if (obj[1] == null){
            throw new Exception("用户信息丢失");
        }
        musicMapper.addUserMusicList(obj);
    }

    @Override
    public ArrayList<Music> getMusicListMusic(Integer listid) {
        if (listid != null){
            return musicMapper.getMusicListMusic(listid);
        }
        return null;
    }

    @Override
    public ArrayList<String> search(String obj) {
        if(obj != null){
            return musicMapper.search(obj);
        }
        return null;
    }
}
