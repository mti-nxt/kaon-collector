package mti.nxt.kao.collector.image;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class KaoImageDownloader {

    private Path downloadPath;

    public KaoImageDownloader(Path downloadPath) {
        this.downloadPath = downloadPath;
    }

    public void downloadByUrl(String url) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpHost target = new HttpHost(url);
        HttpRequest get = new HttpGet();

        final HttpResponse response = client.execute(target, get);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            final InputStream content =
                    response.getEntity().getContent();


            BufferedInputStream inputStream = new BufferedInputStream(content);
            final BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(downloadPath));

        }


    }
}
