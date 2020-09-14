package xyz.prmusic.config.springSecurity.login;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.result.ResultMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//认证失败处理器
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ToJsonUtil toJsonUtil = new ToJsonUtil(ResultMessage.UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.setStatus(toJsonUtil.getStatusCode());
        if (e == null){
            httpServletResponse.getWriter().write(toJsonUtil.getJsonMessage());
        }else{
            toJsonUtil.setMessage(e.getMessage());
            httpServletResponse.getWriter().write(toJsonUtil.getJsonMessage());
        }
    }
}
