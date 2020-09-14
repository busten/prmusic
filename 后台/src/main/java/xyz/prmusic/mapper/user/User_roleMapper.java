package xyz.prmusic.mapper.user;

import org.apache.ibatis.annotations.*;
import xyz.prmusic.entity.user.User_role;

import java.util.ArrayList;

@Mapper
public interface User_roleMapper {
    @Insert("INSERT INTO tb_user_role(rolename,tag) VALUES(${user_role.rolename},${user_role.tag})")
    void addRole(User_role user_role);

    @Delete("<script><foreach collection=\"array\" item=\"user_roles.rid\" open=\"(\" separator=\",\" close=\")\">" +
            "DELETE FROM tb_user_role WHERE uid = #{user_roles.rid};" +
            "</foreach></script>")
    void deleteRole(ArrayList<User_role> user_roles);

    @Update("<script>" +
            "UPDATE tb_user_role SET " +
            "<if test=\"user_role.rolename != null\">rolename = #{user_role.rolename},</if>" +
            "<if test=\"user_role.tag != null\">tag = #{user_role.tag},</if>" +
            "WHERE rid = #{user_role.rid}" +
            "</script>")
    void updateRole(User_role user_role);

    @Select("SELECT * FROM tb_user_role WHERE rid = #{rid}")
    User_role findRoleByRid(Integer rid);
}
