package xyz.prmusic.mapper.music;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.prmusic.entity.music.Music_album;

import java.util.ArrayList;

@Mapper
public interface Music_albumMapper {
    void addAlbum(Music_album music_album);

    void deleteAlbum(Integer[] albumid);

    void updateAlbum(Music_album music_album);

    @Select("SELECT * FROM `tb_music_album` WHERE album_id = #{albumid}")
    Music_album findAlbumById(Integer albumid);

    @Select("SELECT * FROM `tb_music_album`")
    ArrayList<Music_album> getAllAlbum();

    @Select("SELECT * FROM `tb_music_album` order by rand() limit 10")
    ArrayList<Music_album> getAlbumRecommend();

    @Select("SELECT tb_music_album.* FROM `tb_music_album` INNER JOIN " +
            "tb_music_in_active ON tb_music_album.album_id = tb_music_in_active.selectedalbum_id")
    ArrayList<Music_album> getSelectedAlbum();
}
