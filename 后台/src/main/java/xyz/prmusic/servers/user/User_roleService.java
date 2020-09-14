package xyz.prmusic.servers.user;

import xyz.prmusic.entity.user.User_role;
import xyz.prmusic.mapper.user.User_roleMapper;

import java.util.ArrayList;

public interface User_roleService extends User_roleMapper {

    @Override
    void addRole(User_role user_role);

    @Override
    void deleteRole(ArrayList<User_role> user_roles);

    @Override
    void updateRole(User_role user_role);

    @Override
    User_role findRoleByRid(Integer rid);
}
