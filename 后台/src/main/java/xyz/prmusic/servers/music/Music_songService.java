package xyz.prmusic.servers.music;

import org.apache.ibatis.annotations.Mapper;
import xyz.prmusic.entity.music.Music_song;
import xyz.prmusic.mapper.music.Music_songMapper;

import java.util.ArrayList;

@Mapper
public interface Music_songService extends Music_songMapper {
    @Override
    ArrayList<Music_song> getAllSong();
}
