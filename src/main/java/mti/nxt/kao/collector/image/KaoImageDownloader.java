package mti.nxt.kao.collector.image;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class KaoImageDownloader {

    private String downloadPath;
    private final int BUFFER_SIZE = 10240;

    public KaoImageDownloader(String downloadPath) {
        this.downloadPath = downloadPath;
    }


    public void downloadByUrl(String url,String fileName) throws IOException, URISyntaxException {

        HttpClient client = HttpClientBuilder.create().build();
        final HttpGet get = new HttpGet();
        get.setURI(new URI(url));

        final HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            final InputStream content =
                    response.getEntity().getContent();


            try (
                    final BufferedInputStream inputStream = new BufferedInputStream(content);
                    final BufferedOutputStream outputStream =
                            new BufferedOutputStream(Files.newOutputStream(Paths.get(downloadPath +fileName)));
            ) {
                byte buffer[] = new byte[BUFFER_SIZE];
                int size = -1;
                while ((size = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, size);
                }
                outputStream.flush();
            }

        }


    }
}
