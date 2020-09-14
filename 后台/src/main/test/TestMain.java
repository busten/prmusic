import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.prmusic.utils.crypto.AESUtil;
import xyz.prmusic.utils.email.EmailUtil;
import xyz.prmusic.utils.token.JwtToken;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestMain {
    @Test
    public void test() throws Exception {
        Object[] aa={"卧槽",1};
        System.out.println(AESUtil.aesDecrypt("PoPQSRvZ4TLE7/7tM1dZCnP5HCPCZ/bi5BcVXb1Tf2d7do3jH2lpHiU4UV2R7nJhA+e0m0ugSs4pj+ooi7cMlzBMJpgFybx5fix6TYmALxo","Dn0lfj3sGGh73XOZ"));
    }
}
