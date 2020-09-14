package xyz.prmusic.utils.token;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.UUID;

public class JwtToken {
    private static final String JWT_ID = UUID.randomUUID().toString();//jwt唯一身份标识
    private static final String JWT_KEY = "wordfuck";//加密密文，私钥
    private static final int EXPIRE_TIME = 1000*60*60*24*7;//有效时间
    private static final String issusr = "xBLNtM4IQrIxm7Gq";
    //由字符串生成加密key
    public static SecretKey secretKey(){
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(JWT_KEY);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    //生成jwtToken
    public static String createJwtToken(String audience){
        //指定请求头签名的算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //创建payload的私有声明
//        Map<String,Object> claims = new HashMap<>();
        //jti的用户id
        String jwtId = JWT_ID;
        //生成jwt的时间
        long newTime = System.currentTimeMillis();
        Date issuedAt = new Date(newTime);
        //生成签名使用的密钥，服务器的私钥，禁止外露
        SecretKey key = secretKey();
        // 为payload添加各种标准声明和私有声明
        JwtBuilder builder = Jwts.builder()
//                .setClaims(claims) // 如果有私有声明，一定要先设置自己创建的这个私有声明，这是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明
                .setId(jwtId) // jti(JWT ID)：jwt的唯一身份标识，根据业务需要，可以设置为一个不重复的值，主要用来作为一次性token，从而回避重放攻击
                .setIssuedAt(issuedAt) // iat(issuedAt)：jwt的签发时间
                .setIssuer(issusr) // iss(issuer)：jwt签发者
//                .setSubject(subject) // sub(subject)：jwt所面向的用户，放登录的用户名，一个json格式的字符串，可存放userid，roldid之类，作为用户的唯一标志
                .signWith(signatureAlgorithm, key); // 设置签名，使用的是签名算法和签名使用的秘钥
        //设置过期时间
        long expTime = EXPIRE_TIME;
        if (expTime >= 0){
            long exp = newTime + expTime;
            builder.setExpiration(new Date(exp));
        }
        //设置jwt接收者
        if (audience == null || audience.equals("")){
            builder.setAudience("prmusic_anymoreuser");
        }else {
            builder.setAudience(audience);
        }
        return builder.compact();
    }

    //解密jwt
    public static Claims parseJWT(String jwtToken){
        SecretKey key = secretKey();// 签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()// 得到默认的JwtParser
                .setSigningKey(key)// 设置签名的秘钥
                .parseClaimsJws(jwtToken).getBody();// 设置需要解析的jwt
        return claims;
    }
}
