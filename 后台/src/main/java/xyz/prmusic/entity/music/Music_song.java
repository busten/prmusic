package xyz.prmusic.entity.music;

import java.io.Serializable;

public class Music_song implements Serializable {
    private Integer ms_id;
    private String songname;
    private String create_time;
    private Integer music_number;

    public Music_song() {
    }

    public Music_song(String songname, String create_time, Integer music_number) {
        this.songname = songname;
        this.create_time = create_time;
        this.music_number = music_number;
    }

    public Integer getMs_id() {
        return ms_id;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getMusic_number() {
        return music_number;
    }

    public void setMusic_number(Integer music_number) {
        this.music_number = music_number;
    }

    @Override
    public String toString() {
        return "Music_song{" +
                "ms_id=" + ms_id +
                ", songname='" + songname + '\'' +
                ", create_time='" + create_time + '\'' +
                ", music_number=" + music_number +
                '}';
    }
}
