package xyz.prmusic.entity.user;

import java.io.Serializable;

public class User implements Serializable {
    private Integer uid;
    private String account;
    private String password;
    private String username;
    private String create_time;
    private String email;
    private String last_login_time;
    private String last_login_ip;

    public User() {
    }

    public User(Integer uid, String account, String password, String username, String create_time, String email, String last_login_time, String last_login_ip) {
        this.uid = uid;
        this.account = account;
        this.password = password;
        this.username = username;
        this.create_time = create_time;
        this.email = email;
        this.last_login_time = last_login_time;
        this.last_login_ip = last_login_ip;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", create_time='" + create_time + '\'' +
                ", email='" + email + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                ", last_login_ip='" + last_login_ip + '\'' +
                '}';
    }
}
