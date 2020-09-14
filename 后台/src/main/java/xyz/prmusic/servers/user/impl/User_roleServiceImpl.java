package xyz.prmusic.servers.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.prmusic.entity.user.User_role;
import xyz.prmusic.mapper.user.User_roleMapper;
import xyz.prmusic.servers.user.User_roleService;

import java.util.ArrayList;

@Service
public class User_roleServiceImpl implements User_roleService {
    @Autowired
    private User_roleMapper user_roleMapper;
    @Override
    public void addRole(User_role user_role) {
        if (user_role == null){
            try {
                throw new Exception("角色信息不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        user_roleMapper.addRole(user_role);
    }

    @Override
    public void deleteRole(ArrayList<User_role> user_roles) {
        if (user_roles.size() == 0){
            try {
                throw new Exception("角色信息不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        user_roleMapper.deleteRole(user_roles);
    }

    @Override
    public void updateRole(User_role user_role) {
        if (user_role == null){
            try {
                throw new Exception("角色信息不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        user_roleMapper.updateRole(user_role);
    }

    @Override
    public User_role findRoleByRid(Integer rid) {
        if (rid == null){
            try {
                throw new Exception("角色信息不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user_roleMapper.findRoleByRid(rid);
    }
}
