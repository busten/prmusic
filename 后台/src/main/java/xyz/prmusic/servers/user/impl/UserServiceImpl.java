package xyz.prmusic.servers.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.prmusic.entity.user.User;
import xyz.prmusic.entity.user.User_auth;
import xyz.prmusic.entity.user.User_role;
import xyz.prmusic.mapper.user.UserMapper;
import xyz.prmusic.servers.user.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("用户不能为空");
        }
        if (user.getPassword().equals("")) {
            throw new Exception("密码不能为空");
        }
        if (user.getUsername().equals("")) {
            throw new Exception("账号名称不能为空");
        }
        if (userMapper.findUserByAccountOrEmail(user.getAccount()) != null){
            throw new Exception("此账号已被注册");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreate_time(simpleDateFormat.format(new Date()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
    }

    @Override
    public void deleteUser(ArrayList<User> users) {
        if (users.size() == 0) {
            try {
                throw new Exception("用户不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userMapper.deleteUser(users);
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            try {
                throw new Exception("用户不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByAccountOrEmail(String obj) {
        if (obj == null || obj.equals("")) {
            try {
                throw new Exception("需要填入用户账号或者邮箱");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        User users = userMapper.findUserByAccountOrEmail(obj);
        return users;
    }

    @Override
    public ArrayList<User_auth> findAuthByUid(Integer uid) {
        if (uid == null) {
            try {
                throw new Exception("查询条件不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userMapper.findAuthByUid(uid);
    }

    @Override
    public ArrayList<User_role> findRoleByUid(Integer uid) {
        if (uid == null) {
            try {
                throw new Exception("查询条件不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userMapper.findRoleByUid(uid);
    }
}
