package xyz.prmusic.mapper.user;

import org.apache.ibatis.annotations.*;
import xyz.prmusic.entity.user.User;
import xyz.prmusic.entity.user.User_auth;
import xyz.prmusic.entity.user.User_role;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO " +
            "tb_user(account,password,username,create_time,email) " +
            "VALUES(#{user.account},#{user.password},#{user.username},#{user.create_time},#{user.email})")
    void addUser(@Param("user") User user) throws Exception;

    @Delete("<script>" +
            "DELETE FROM tb_user WHERE " +
            "<foreach collection=\"list\" item=\"user\" separator=\"OR\">" +
            "uid = #{user.uid};" +
            "</foreach>" +
            "</script>")
    void deleteUser(ArrayList<User> users);

    @Update("<script>" +
            "UPDATE tb_user SET " +
            "<if test=\"users.password != null\">password = #{users.password},</if>" +
            "<if test=\"users.username != null\">username = #{users.username},</if>" +
            "<if test=\"users.email != null\">email = #{users.email},</if>" +
            "<if test=\"users.last_login_time != null\">last_login_time = #{users.last_login_time},</if>" +
            "<if test=\"users.last_login_ip != null\">last_login_ip = #{users.last_login_ip},</if>" +
            "WHERE uid = #{users.uid}" +
            "</script>")
    void updateUser(User user);

    @Select("SELECT * FROM tb_user WHERE account = #{obj} OR email = #{obj}")
    User findUserByAccountOrEmail(String obj);

    @Select("SELECT tb_user_auth.* FROM tb_user_auth" +
            " INNER JOIN tb_role_in_auth ON tb_role_in_auth.auth_id = tb_user_auth.auth_id " +
            "INNER JOIN tb_user_role ON tb_user_role.rid = tb_role_in_auth.rid " +
            "INNER JOIN tb_user_in_role ON tb_user_in_role.rid = tb_user_role.rid " +
            "INNER JOIN tb_user ON tb_user.uid = tb_user_in_role.uid " +
            "WHERE tb_user.uid = #{uid}")
    ArrayList<User_auth> findAuthByUid(Integer uid);

    @Select("SELECT tb_user_role.* FROM tb_user_role " +
            "INNER JOIN tb_user_in_role ON tb_user_in_role.rid = tb_user_role.rid " +
            "INNER JOIN tb_user ON tb_user.uid = tb_user_in_role.uid " +
            "WHERE tb_user.uid = #{uid}")
    ArrayList<User_role> findRoleByUid(Integer uid);
}
