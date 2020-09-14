package xyz.prmusic.config.springSecurity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.prmusic.entity.user.User;
import xyz.prmusic.servers.user.UserService;
import xyz.prmusic.utils.readerToMap.ReaderToMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CustomJSONUserLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UserService userService;

    public CustomJSONUserLoginFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Map<String, String> obj;
        String username;
        String password;
        String captcha;
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        try {
            obj = ReaderToMap.getObjToMap(request.getReader(), true);
            username = obj.get("username");
            password = obj.get("password");
            captcha = obj.get("captcha");
            request.setAttribute("rememberme", obj.get("rememberme"));
            request.setAttribute("username",username);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (username == null || username.equals("")) {
            throw new AuthenticationServiceException("账号不能为空");
        }
        if (password == null || password.equals("")) {
            throw new AuthenticationServiceException("密码不能为空");
        }
        if (captcha == null || captcha.equals("")){
            throw new AuthenticationServiceException("验证码不能为空");
        }
        if (captcha.length() != 6){
            throw new AuthenticationServiceException("验证码格式不正确");
        }
        if (!captcha.equalsIgnoreCase(verificationCode)){
            throw new AuthenticationServiceException("验证码错误");
        }
        User user = userService.findUserByAccountOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("没有这个用户");
        }
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new AuthenticationServiceException("密码错误");
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword(), new ArrayList<>()));
    }
}
