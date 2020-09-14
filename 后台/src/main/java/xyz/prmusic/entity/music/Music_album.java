package xyz.prmusic.entity.music;

import java.io.Serializable;

public class Music_album implements Serializable {
    private Integer album_id;
    private String albumname;
    private String create_time;
    private String introduce;
    private String img_url;
    private Integer music_number;

    public Music_album() {
    }

    public Music_album(String albumname, String create_time, String introduce, String img_url, Integer music_number) {
        this.albumname = albumname;
        this.create_time = create_time;
        this.introduce = introduce;
        this.img_url = img_url;
        this.music_number = music_number;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getMusic_number() {
        return music_number;
    }

    public void setMusic_number(Integer music_number) {
        this.music_number = music_number;
    }

    @Override
    public String toString() {
        return "Music_album{" +
                "album_id=" + album_id +
                ", albumname='" + albumname + '\'' +
                ", create_time='" + create_time + '\'' +
                ", introduce='" + introduce + '\'' +
                ", img_url='" + img_url + '\'' +
                ", music_number=" + music_number +
                '}';
    }
}
