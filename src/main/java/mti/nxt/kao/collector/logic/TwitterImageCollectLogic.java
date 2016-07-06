package mti.nxt.kao.collector.logic;

import mti.nxt.kao.collector.image.KaoImageDownloader;
import mti.nxt.kao.collector.twitter.TwitterClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class TwitterImageCollectLogic {

    // TODO 対象ユーザの渡し方
    String userName = "@masaosaan";
    Path imagePath =  Paths.get("/var/kao/" +userName.substring(2));

    public void execute() {

        final TwitterClient twitterClient = new TwitterClient();
        final List<String> mediaURLList = twitterClient.getMediaURLList(userName);

        KaoImageDownloader downloader = new KaoImageDownloader(imagePath);

        mediaURLList.forEach(url -> {
            try {
                downloader.downloadByUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
