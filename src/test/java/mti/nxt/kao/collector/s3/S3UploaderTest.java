package mti.nxt.kao.collector.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by masakkuma on 2016/07/08.
 */
public class S3UploaderTest {

    Properties properties = new Properties();

    {
        try (InputStream inputStream = new FileInputStream("kaocollector.properties")) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadTest() throws IOException {
        S3Uploader uploader = new S3Uploader("test");
        String imagesDirPath = "kaotest/upload";
        uploader.upload(imagesDirPath);

        AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("aws.accessKey"),properties.getProperty("aws.accessSecretKey"));

        final AmazonS3Client s3Client = new AmazonS3Client(credentials);

        final ListObjectsV2Result listObjects = s3Client.listObjectsV2("kao-class-dev","images/test");
        final List<S3ObjectSummary> objectSummaries = listObjects.getObjectSummaries();
        final ArrayList<String> objectNames = new ArrayList<>();

        objectSummaries.forEach(object -> {
            objectNames.add(object.getKey());
            System.out.println(object.getKey());
        });

        Files.list(Paths.get(imagesDirPath)).forEach(file -> {
            assertThat(objectNames,hasItem("images/test/" + file.getFileName().toString()));
            System.out.println(file.getFileName().toString());
        });

    }

}