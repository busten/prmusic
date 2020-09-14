package xyz.prmusic.entity.user;

import java.io.Serializable;

public class User_role implements Serializable {
    private Integer rid;
    private String rolename;
    private String tag;

    public User_role() {
    }

    public User_role(String rolename, String tag) {
        this.rolename = rolename;
        this.tag = tag;
    }

    public Integer getRid() {
        return rid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "User_role{" +
                "rid=" + rid +
                ", rolename='" + rolename + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
