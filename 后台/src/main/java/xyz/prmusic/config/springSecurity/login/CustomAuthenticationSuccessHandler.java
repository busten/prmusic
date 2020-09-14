package xyz.prmusic.config.springSecurity.login;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import xyz.prmusic.utils.crypto.AESUtil;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.readerToMap.ReaderToMap;
import xyz.prmusic.utils.redis.RedisUtil;
import xyz.prmusic.utils.result.ResultMessage;
import xyz.prmusic.utils.token.JwtToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//认证成功处理器
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedisUtil redisUtil;

    public CustomAuthenticationSuccessHandler() {
    }

    public CustomAuthenticationSuccessHandler(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        String rem = String.valueOf(request.getAttribute("rememberme"));
        String username = String.valueOf(request.getAttribute("username"));
        ToJsonUtil toJsonUtil = new ToJsonUtil(ResultMessage.SUCCESS);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setStatus(toJsonUtil.getStatusCode());
        if (Boolean.parseBoolean(rem)){
            String jwtToken = JwtToken.createJwtToken(authResult.getName());
            SecurityContextHolder.getContext().setAuthentication(authResult);
            if (redisUtil.getKey(username+"token").equals("null")){
                redisUtil.addTimeLimit(username+"token",jwtToken,60*60*24*7L);
            }else {
                redisUtil.add(username+"token",jwtToken);
            }
            response.setHeader("retoken",jwtToken);
        }
        response.getWriter().write(toJsonUtil.getJsonMessage());
    }
}
