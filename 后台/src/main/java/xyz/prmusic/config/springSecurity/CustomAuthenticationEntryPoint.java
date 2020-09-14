package xyz.prmusic.config.springSecurity;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.result.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//认证入口(未登录)

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ToJsonUtil toJsonUtil = new ToJsonUtil(ResultMessage.UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.setStatus(toJsonUtil.getStatusCode());
        if (e != null){
            toJsonUtil.setMessage(e.getMessage());
            if (e.getMessage().equals("Full authentication is required to access this resource")){
                toJsonUtil.setMessage("此操作需要通过密码登陆");
            }
        }
        httpServletResponse.getWriter().write(toJsonUtil.getJsonMessage());
    }
}
