package mti.nxt.kao.collector.s3;

import org.junit.Test;

/**
 * Created by masakkuma on 2016/07/08.
 */
public class S3UploaderTest {

    @Test
    public void uploadTest() {
        S3Uploader uploader = new S3Uploader();
        String imagesDirPath = "kaotest/upload";
        uploader.upload(imagesDirPath);


    }

}