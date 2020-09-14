package xyz.prmusic.config.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.prmusic.entity.user.User;
import xyz.prmusic.entity.user.User_auth;
import xyz.prmusic.entity.user.User_role;
import xyz.prmusic.servers.user.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class DbUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByAccountOrEmail(s);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user != null){
            ArrayList<User_auth> user_auths = userService.findAuthByUid(user.getUid());
            for (User_auth user_auth : user_auths) {
                GrantedAuthority authority = new SimpleGrantedAuthority(user_auth.getAuth_name());
                authorities.add(authority);
            }
            ArrayList<User_role> user_roles = userService.findRoleByUid(user.getUid());
            for (User_role user_role : user_roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user_role.getRolename());
                authorities.add(authority);
            }
        }else {
            throw new UsernameNotFoundException("用户不存在！");
        }
        org.springframework.security.core.userdetails.User user1 =
                new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassword(),authorities);
        return user1;
    }
}
