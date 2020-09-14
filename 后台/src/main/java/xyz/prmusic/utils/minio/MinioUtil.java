package xyz.prmusic.utils.minio;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@PropertySource("classpath:minio.properties")
public class MinioUtil {
    @Value("${bucket}")
    private String bucket;

    private final MinioClient minioClient;

    @Autowired
    public MinioUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    //上传文件,获取文件内容
    public String uploadFile(MultipartFile file) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes())) {
            return uploadFile(bais, file.getOriginalFilename());
        } catch (IOException e) {
            throw new IOException("读取文件失败");
        }
    }

    //上传文件,解析文件并上传至服务器
    public String uploadFile(InputStream stream, String fileName) {
        //标识文件类型
        String coenttype = "*";
        //存储位置
        String path = "file/";
        String fileTyles = fileName.substring(fileName.lastIndexOf("."));
        String fileTyle = fileTyles.replace(".", "");
        String[] filefromat = {"mp3", "mp4", "flac", "mp4", "flv", "ogg", "png", "jpg"};
        for (int i = 0; i < filefromat.length; i++) {
            if (fileTyle.equals(filefromat[i])) {
                if (i < 3) {
                    coenttype = "audio/" + filefromat[i];
                    path = "audio/";
                }
                if (i < 6 && i > 2) {
                    coenttype = "video/" + filefromat[i];
                    path = "video/";
                }
                if (i > 5) {
                    coenttype = "image/" + filefromat[i];
                    path = "image/";
                }
            }
        }
        StringBuilder objectName = new StringBuilder(path);
        objectName.append(fileName);
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(objectName.toString()).stream(
                            stream, stream.available(), 10485760)
                            .contentType(coenttype)
                            .build());
            return objectName.toString();
        } catch (Exception e) {
            return "";
        }
    }

    //获取文件
    public InputStream getFile(String objectName) throws Exception {
        // 设置密钥参数
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        String key = sb.toString();
        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("algorithmDES");
        DESKeySpec keySpec = new DESKeySpec(keyByte);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        ServerSideEncryptionCustomerKey serverSideEncryptionCustomerKey = new ServerSideEncryptionCustomerKey(secretKey);
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket("my-bucketname")
                        .object("my-objectname")
                        .ssec(serverSideEncryptionCustomerKey)
                        .build())) {
            return stream;
        } catch (Exception e) {
            throw new Exception("获取文件错误");
        }
    }

    //直接下载文件
    public void lookUploadFile(HttpServletRequest req, HttpServletResponse res, String fid, String fileName) {
        try (InputStream in = getFile(fid);
             OutputStream output = res.getOutputStream()) {
            // 得到输入流
            if (in == null) {
                PrintWriter printWriter = res.getWriter();
                    printWriter.append("404 - File Not Exist");
                return;
            }
            res.reset();
            res.addHeader("content-Disposition", "inline;filename=" + java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            byte[] b = new byte[4096];
            int i;
            while ((i = in.read(b)) > 0) {
                output.write(b, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取文件的外部url
    public String getMusicUrl(String objectName) throws Exception {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object("audio/"+objectName)
                            .expiry(7,TimeUnit.MINUTES)
                            .build());
        } catch (Exception e) {
            throw new Exception("获取url失败");
        }
    }

    public String getImgUrl(String objectName) throws Exception {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object("image/"+objectName)
                            .expiry(7,TimeUnit.MINUTES)
                            .build());
        } catch (Exception e) {
            throw new Exception("获取url失败");
        }
    }
}
