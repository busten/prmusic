package xyz.prmusic.servers.user;

import xyz.prmusic.entity.user.User;
import xyz.prmusic.entity.user.User_auth;
import xyz.prmusic.entity.user.User_role;
import xyz.prmusic.mapper.user.UserMapper;

import java.util.ArrayList;

public interface UserService extends UserMapper {
    @Override
    void addUser(User user) throws Exception;

    @Override
    void deleteUser(ArrayList<User> users);

    @Override
    void updateUser(User user);

    @Override
    User findUserByAccountOrEmail(String obj);

    @Override
    ArrayList<User_auth> findAuthByUid(Integer uid);

    @Override
    ArrayList<User_role> findRoleByUid(Integer uid);
}
