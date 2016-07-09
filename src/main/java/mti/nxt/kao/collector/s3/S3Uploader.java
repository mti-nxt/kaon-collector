package mti.nxt.kao.collector.s3;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by masakkuma on 2016/07/08.
 */
public class S3Uploader {

    private Properties properties = new Properties();
    private AmazonS3Client s3Client;
    private String userName;

    {
        try (InputStream inputStream = new FileInputStream("kaocollector.properties")) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public S3Uploader() {
        s3Client = new AmazonS3Client(new BasicAWSCredentials(properties.getProperty("aws.accessKey"), properties.getProperty("aws.accessSecretKey")));
    }

    public S3Uploader(String userName) {
        this();
        this.userName = userName;
    }

    public void upload(String imagePath) throws IOException {
        upload(Paths.get(imagePath));
    }

    private void upload(Path path) throws IOException {

        Files.list(path).forEach(file -> {
            s3Client.putObject("kao-class-dev","images/" + userName+"/"+file.getFileName().toString(),file.toFile());
        });

    }
}
