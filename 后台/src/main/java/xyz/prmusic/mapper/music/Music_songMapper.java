package xyz.prmusic.mapper.music;

import org.apache.ibatis.annotations.Select;
import xyz.prmusic.entity.music.Music_song;

import java.util.ArrayList;

public interface Music_songMapper {
    @Select("SELECT * FROM tb_music_song")
    ArrayList<Music_song> getAllSong();
}
