package xyz.prmusic.webcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.prmusic.config.springMvc.MvcDataEncryption;
import xyz.prmusic.entity.user.User;
import xyz.prmusic.servers.user.UserService;
import xyz.prmusic.utils.email.EmailUtil;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.redis.RedisUtil;
import xyz.prmusic.utils.validateCode.RandomValidateCode;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class AuthenticationController {
    private ToJsonUtil jsonUtil = new ToJsonUtil();
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/user/verificationCode")
    public void verificationCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            RandomValidateCode randomValidateCode = new RandomValidateCode();
            randomValidateCode.getRandcode(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/user/register")
    public void register(MvcDataEncryption request, HttpServletResponse response) throws Exception {
        Map<String, String> reuser = request.getDataFromMap(true);
        User user = new User();
        user.setAccount(reuser.get("account"));
        user.setUsername(reuser.get("username"));
        user.setPassword(reuser.get("password"));
        user.setEmail(reuser.get("email"));
        if (user.getEmail().equals("")) {
            throw new Exception("邮箱地址不能为空");
        }
        if (user.getAccount().equals("")) {
            throw new Exception("账号不能为空");
        }
        if (reuser.get("captcha").equals("")) {
            throw new Exception("验证码不能为空");
        }
        if (!reuser.get("captcha").equalsIgnoreCase(redisUtil.getKey(user.getEmail() + "captcha"))) {
            throw new Exception("验证码错误");
        }
        userService.addUser(user);
    }

    @RequestMapping("/user/getcode")
    public void getcode(MvcDataEncryption request,HttpServletResponse response) throws Exception {
        String email = request.getDataFromMap(true).get("email");
        if (email.equals("") || email == null) {
            throw new Exception("邮箱地址不能为空！");
        }
        int randoms = (int) ((Math.random() * 9 + 1) * 100000);
        redisUtil.addTimeLimit(email + "captcha",randoms,300l);
        boolean bol = new EmailUtil().registerMail(email, randoms);
        if (bol){
            sendmessage(response,"已发送验证码到您的邮箱,请注意查收");
        }
    }

    private void sendmessage(HttpServletResponse response,String message) throws IOException {
        jsonUtil.setStatusCode(200);
        jsonUtil.setMessage(message);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setStatus(jsonUtil.getStatusCode());
        response.getWriter().write(jsonUtil.getJsonMessage());
    }
}
