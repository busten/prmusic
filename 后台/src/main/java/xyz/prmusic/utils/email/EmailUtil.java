package xyz.prmusic.utils.email;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
    private static String protocol = "smtp";
    private static String host = "smtpout.secureserver.net";
    private static String port = "465";
    private static String auth = "true";
    private static String account = "admin@prmusic.xyz";
    private static String password = "#cW6-PP*KQ";
    private static String send = "admin@prmusic.xyz";

    public static void sendMail(String receive,String content,String title) throws UnsupportedEncodingException, MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol",protocol);
        properties.setProperty("mail.smtp.host",host);
        properties.setProperty("mail.smtp.prot",port);
        properties.setProperty("mail.smtp.auth",auth);
        properties.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(properties);
        MimeMessage mimeMessage = createMimeMessage(session,send,receive,content,title);
        Transport transport = session.getTransport();
        transport.connect(account,password);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }

    public static MimeMessage createMimeMessage(Session session,String send,String receive,String content,String title) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        Address address = new InternetAddress(send,"prmusic","UTF-8");
        mimeMessage.setFrom(address);
        mimeMessage.setSubject(title,"UTF-8");
        mimeMessage.setContent(content,"text/html;charset=utf-8");
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receive,"UTF-8"));
        mimeMessage.setSentDate(new Date());
        mimeMessage.saveChanges();
        return mimeMessage;
    }

    public boolean registerMail(String receive,int code){
        String title = "这是您的验证码!5分钟内有效";
        String content = "<h3>您在prmusic的验证码为<a style=\"color:blue\">"+code+"</a>,在5分钟之内有效!</h3>";
        try {
            sendMail(receive,content,title);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
