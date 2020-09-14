package xyz.prmusic.config.springMvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.result.ResultMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class MvcCustomExceptionResolver {

    @ExceptionHandler
    public void exction(HttpServletResponse response,Exception e) throws IOException {
        ToJsonUtil toJsonUtil = new ToJsonUtil(ResultMessage.UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setStatus(toJsonUtil.getStatusCode());
        toJsonUtil.setMessage(e.getMessage());
        response.getWriter().write(toJsonUtil.getJsonMessage());
    }
}
