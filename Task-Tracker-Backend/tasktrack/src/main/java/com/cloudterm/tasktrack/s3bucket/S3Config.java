package com.cloudterm.tasktrack.s3bucket;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.cloudterm.tasktrack.repository.DbConstants.*;

@Configuration
public class S3Config {
    @Bean
    public AmazonS3 amazonS3(){
        AWSCredentials awsCredentials = new BasicSessionCredentials(
                AWS_ACCESS_KEY,
                AWS_SECRET_ACCESS_KEY,
                AWS_SESSION_TOKEN
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("us-east-1")
                .build();
    }
}
