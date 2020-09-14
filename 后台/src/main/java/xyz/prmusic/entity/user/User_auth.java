package xyz.prmusic.entity.user;

import java.io.Serializable;

public class User_auth implements Serializable {
    private Integer auth_id;
    private String auth_name;
    private String auth_tag;

    public User_auth() {
    }

    public User_auth(String auth_name, String auth_tag) {
        this.auth_name = auth_name;
        this.auth_tag = auth_tag;
    }

    public Integer getAuth_id() {
        return auth_id;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }

    public String getAuth_tag() {
        return auth_tag;
    }

    public void setAuth_tag(String auth_tag) {
        this.auth_tag = auth_tag;
    }

    @Override
    public String toString() {
        return "User_auth{" +
                "auth_id=" + auth_id +
                ", auth_name='" + auth_name + '\'' +
                ", auth_tag='" + auth_tag + '\'' +
                '}';
    }
}
