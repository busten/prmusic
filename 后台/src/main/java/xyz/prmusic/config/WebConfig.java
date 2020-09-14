package xyz.prmusic.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import xyz.prmusic.config.springMvc.SpringMvcConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //启动初始化
            AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            //注册springMVC配置
            context.register(SpringMvcConfig.class);
            //设置servletContext上下文
            context.setServletContext(servletContext);
    }
}
