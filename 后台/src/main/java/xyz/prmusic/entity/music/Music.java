package xyz.prmusic.entity.music;

import java.io.Serializable;

public class Music implements Serializable {
    Integer music_id;
    String musicname;
    String create_time;
    String url;
    String songname;
    String albumname;
    String img_url;

    public Music() {
    }

    public Music(Integer music_id, String musicname, String create_time, String url, String songname, String albumname, String img_url) {
        this.music_id = music_id;
        this.musicname = musicname;
        this.create_time = create_time;
        this.url = url;
        this.songname = songname;
        this.albumname = albumname;
        this.img_url = img_url;
    }

    public Integer getMusic_id() {
        return music_id;
    }

    public void setMusic_id(Integer music_id) {
        this.music_id = music_id;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Music{" +
                "music_id=" + music_id +
                ", musicname='" + musicname + '\'' +
                ", create_time='" + create_time + '\'' +
                ", url='" + url + '\'' +
                ", songname='" + songname + '\'' +
                ", albumname='" + albumname + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
