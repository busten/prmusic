package xyz.prmusic.config.springSecurity;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.result.ResultMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//拒绝访问处理器（登录状态下，无权限会触发）
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @ExceptionHandler(AccessDeniedException.class)
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ToJsonUtil toJsonUtil = new ToJsonUtil(ResultMessage.FORBIDDEN);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.setStatus(toJsonUtil.getStatusCode());
        httpServletResponse.getWriter().write(toJsonUtil.getJsonMessage());
    }
}
