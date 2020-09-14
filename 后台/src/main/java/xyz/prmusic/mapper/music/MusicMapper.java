package xyz.prmusic.mapper.music;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import xyz.prmusic.entity.music.Music;
import xyz.prmusic.entity.music.Music_user_list;

import java.sql.JDBCType;
import java.util.ArrayList;

@Mapper
public interface MusicMapper {

    @Insert("INSERT INTO tb_music(musicname,create_time,url,img_url) VALUES(#{music.musicname},#{music.create_time},#{music.url}.#{music.img_url});")
    void addMusic(@Param("music") Music music) throws Exception;

    @Delete("<script>" +
            "DELETE FROM tb_music WHERE " +
            "<foreach collection=\"array\" item=\"musicsid\" separator=\"OR\">" +
            "music_id = #{musicsid};" +
            "</foreach>" +
            "</script>")
    void delete(Integer[] musicsid) throws Exception;

    @Update("<script>" +
            "UPDATE tb_music SET " +
            "<if test=\"music.musicname != null\">musicname = #{music.musicname},</if>" +
            "<if test=\"music.url != null\">url = #{music.url},</if>" +
            "WHERE music_id = #{music.music_id}" +
            "</script>")
    void update(@Param("music") Music music) throws Exception;

    @Select("<script>" +
            "SELECT tb_music.*,tb_music_song.songname,tb_music_album.albumname FROM tb_music " +
            "INNER JOIN tb_music_in_song ON tb_music.music_id = tb_music_in_song.music_id " +
            "INNER JOIN tb_music_song ON tb_music_song.ms_id = tb_music_in_song.ms_id " +
            "INNER JOIN tb_music_in_album ON tb_music_in_album.music_id = tb_music.music_id " +
            "INNER JOIN tb_music_album ON tb_music_album.album_id = tb_music_in_album.album_id WHERE " +
            "<foreach collection=\"array\" separator=\"OR\" item=\"item\">" +
            "tb_music.music_id = #{item}" +
            "</foreach>" +
            "</script>")
    ArrayList<Music> findMusicByMusicId(Integer[] musicsid) throws Exception;

    @Select("<script>" +
            "SELECT tb_music.*,tb_music_song.songname,tb_music_album.albumname FROM tb_music " +
            "INNER JOIN tb_music_in_song ON tb_music.music_id = tb_music_in_song.music_id " +
            "INNER JOIN tb_music_song ON tb_music_song.ms_id = tb_music_in_song.ms_id " +
            "INNER JOIN tb_music_in_album ON tb_music_in_album.music_id = tb_music.music_id " +
            "INNER JOIN tb_music_album ON tb_music_album.album_id = tb_music_in_album.album_id WHERE " +
            "<foreach collection=\"array\" separator=\"OR\" item=\"item\">" +
            "tb_music_album.album_id = #{item}" +
            "</foreach>" +
            "</script>")
    ArrayList<Music> findMusicByAlbumId(Integer[] albumid) throws Exception;

    @Select("SELECT tb_music.*,tb_music_song.songname,tb_music_album.albumname FROM tb_music " +
            "INNER JOIN tb_music_in_song ON tb_music.music_id = tb_music_in_song.music_id " +
            "INNER JOIN tb_music_song ON tb_music_song.ms_id = tb_music_in_song.ms_id " +
            "INNER JOIN tb_music_in_album ON tb_music_in_album.music_id = tb_music.music_id " +
            "INNER JOIN tb_music_album ON tb_music_album.album_id = tb_music_in_album.album_id order by rand() limit 10")
    ArrayList<Music> getRandom();

    @Select("SELECT tb_music.* FROM `tb_music` " +
            "INNER JOIN tb_music_in_active ON tb_music_in_active.newmusic_id = tb_music.music_id")
    ArrayList<Music> getNewMusic();

    @Select("SELECT tb_music.*,tb_music_song.songname FROM tb_music " +
            "INNER JOIN tb_music_in_song ON tb_music_in_song.music_id = tb_music.music_id " +
            "INNER JOIN tb_music_song ON tb_music_in_song.ms_id = tb_music_song.ms_id " +
            "WHERE tb_music_song.ms_id = #{song_id}")
    ArrayList<Music> getSongMusic(Integer song_id);

    @Select("SELECT tb_music.*,tb_music_song.songname FROM tb_music " +
            "INNER JOIN tb_music_in_song ON tb_music_in_song.music_id = tb_music.music_id " +
            "INNER JOIN tb_music_song ON tb_music_in_song.ms_id = tb_music_song.ms_id ")
    ArrayList<Music> getAllMusic();

    @Select("SELECT tb_user_music_list.music_list_id,tb_user_music_list.music_list_name FROM tb_user_music_list" +
            " INNER JOIN tb_user ON tb_user_music_list.uid = tb_user.uid " +
            "WHERE tb_user.uid = #{uid}")
    ArrayList<Music_user_list> getUserMusicListByUid(Integer uid);

    @Insert("INSERT INTO tb_user_music_list(music_list_name,uid) VALUES(#{array[0]},#{array[1]});")
    void addUserMusicList(Object[] obj) throws Exception;

    @Select("SELECT tb_music.*,tb_music_song.songname FROM tb_music " +
            "INNER JOIN tb_music_in_song ON tb_music_in_song.music_id = tb_music.music_id INNER JOIN tb_music_song ON tb_music_song.ms_id = tb_music_in_song.ms_id " +
            "INNER JOIN tb_music_list_in_music ON tb_music.music_id = tb_music_list_in_music.music_id " +
            "INNER JOIN tb_user_music_list ON tb_user_music_list.music_list_id = tb_music_list_in_music.music_list_id " +
            "INNER JOIN tb_user ON tb_user.uid = tb_user_music_list.uid " +
            "WHERE tb_user_music_list.music_list_id = #{uid}")
    ArrayList<Music> getMusicListMusic(Integer listid);

    @Select("SELECT musicname FROM tb_music WHERE musicname like CONCAT('%',#{obj},'%') limit 0,8;")
    @ResultType(java.lang.String.class)
    ArrayList<String> search(String obj);
}
