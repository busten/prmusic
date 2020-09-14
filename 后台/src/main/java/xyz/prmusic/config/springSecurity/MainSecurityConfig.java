package xyz.prmusic.config.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import xyz.prmusic.config.springSecurity.autologin.AutoLoginFilter;
import xyz.prmusic.config.springSecurity.login.CustomAuthenticationFailureHandler;
import xyz.prmusic.config.springSecurity.login.CustomAuthenticationSuccessHandler;
import xyz.prmusic.config.springSecurity.logout.CustomLogoutSuccessHandler;
import xyz.prmusic.servers.user.UserService;
import xyz.prmusic.utils.redis.RedisUtil;

import javax.annotation.Resource;

@Configuration
public class MainSecurityConfig {
    private static DbUserDetailsService dbUserDetailsService;
    private static CustomAuthenticationProvider customAuthenticationProvider;
    private static UserService userService;
    private static RedisUtil redisUtil;

    @Resource
    public void setDbUserDetailsService(DbUserDetailsService dbUserDetailsService) {
        MainSecurityConfig.dbUserDetailsService = dbUserDetailsService;
    }

    @Resource
    public void setCustomAuthenticationProvider(CustomAuthenticationProvider customAuthenticationProvider) {
        MainSecurityConfig.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Resource
    public void setUserService(UserService userService) {
        MainSecurityConfig.userService = userService;
    }

    @Resource
    public void setRedisUtil(RedisUtil redisUtil) {
        MainSecurityConfig.redisUtil = redisUtil;
    }

    //管理员的权限配置
    @Order(1)
    public static class AdminConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest()
                    .fullyAuthenticated()
                    .and()
//                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                    .accessDeniedHandler(new CustomAccessDeniedHandler())
//                    .and()//对未登录和权限不足进行处理，因为在MvcDataEncryption拦截了所有异常导致这里设置的处理器会导致配置失效，
//                    所以不做单独处理(ps:下次记得规范使用异常，不要全用Exction直接抛了...)
                    .cors()
                    .and()
                    .csrf().disable();
            http.addFilter(customJSONLoginFilter(authenticationManager()));
            http.addFilter(new AutoLoginFilter(authenticationManager()));
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(customAuthenticationProvider);
            auth.userDetailsService(dbUserDetailsService);
        }

        private CustomJSONAdminLoginFilter customJSONLoginFilter(AuthenticationManager authenticationManager) {
            CustomJSONAdminLoginFilter customJSONLoginFilter = new CustomJSONAdminLoginFilter(authenticationManager);
            customJSONLoginFilter.setFilterProcessesUrl("/admin/login");
            customJSONLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
            customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
            return customJSONLoginFilter;
        }
    }

    //一般用户的权限配置
    @Order(2)
    public static class UserConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user/**")
                    .authorizeRequests()
                    .antMatchers("/user/login*", "/user/logout", "/user/verificationCode", "/user/register", "/user/getcode").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .logout().logoutUrl("/user/logout").invalidateHttpSession(true)
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
//                    .and()
//                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                    .and()
                    .cors()
                    .and()
                    .csrf().disable();
            http.addFilter(customJSONLoginFilter(authenticationManager()));
            http.addFilter(new AutoLoginFilter(authenticationManager()));
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(customAuthenticationProvider);
            auth.userDetailsService(dbUserDetailsService);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        private CustomJSONUserLoginFilter customJSONLoginFilter(AuthenticationManager authenticationManager) {
            CustomJSONUserLoginFilter customJSONLoginFilter = new CustomJSONUserLoginFilter(authenticationManager, userService);
            customJSONLoginFilter.setFilterProcessesUrl("/user/login");
            customJSONLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
            customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler(redisUtil));
            return customJSONLoginFilter;
        }
    }
}
