package xyz.prmusic.entity.music;

public class Music_user_list {
    private Integer music_list_id;
    private String music_list_name;

    public Music_user_list() {
    }

    public Music_user_list(Integer music_list_id, String music_list_name) {
        this.music_list_id = music_list_id;
        this.music_list_name = music_list_name;
    }

    public Integer getMusic_list_id() {
        return music_list_id;
    }

    public void setMusic_list_id(Integer music_list_id) {
        this.music_list_id = music_list_id;
    }

    public String getMusic_list_name() {
        return music_list_name;
    }

    public void setMusic_list_name(String music_list_name) {
        this.music_list_name = music_list_name;
    }

    @Override
    public String toString() {
        return "Music_user_list{" +
                "music_list_id=" + music_list_id +
                ", music_list_name='" + music_list_name + '\'' +
                '}';
    }
}
