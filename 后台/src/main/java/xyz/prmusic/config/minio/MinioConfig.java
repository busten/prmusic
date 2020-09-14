package xyz.prmusic.config.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:minio.properties")
public class MinioConfig {
    @Value("${url}")
    private String url;
    @Value("${access}")
    private String access;
    @Value("${secret}")
    private String secret;
    @Value("${bucket}")
    private String bucket;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(access, secret)
                .build();
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if(!isExist) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucket)
                            .build());
        }
        return minioClient;
    }
}
