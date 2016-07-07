package mti.nxt.kao.collector.s3;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by masakkuma on 2016/07/08.
 */
public class S3Uploader {
   

    public void upload(String imagePath) {
        upload(Paths.get(imagePath));
    }

    private void upload(Path path) {

    }
}
