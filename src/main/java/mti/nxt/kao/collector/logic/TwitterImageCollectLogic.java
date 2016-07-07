package mti.nxt.kao.collector.logic;

import mti.nxt.kao.collector.image.KaoImageDownloader;
import mti.nxt.kao.collector.twitter.TwitterClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class TwitterImageCollectLogic {

    // TODO 対象ユーザの渡し方
    String userName = "@masaosaan";
    String imagePath =  "/var/kao/" +userName.substring(2) + "/";

    /**
     * 対象のアカウントのtweetから画像を集めてs3バケットにアップする
     */
    public void execute() {

        final TwitterClient twitterClient = new TwitterClient();
        final List<String> mediaURLList = twitterClient.getMediaURLList(userName);
        final List<String> imageList = new ArrayList<>();

        mediaURLList.forEach(url -> {
            try {
                KaoImageDownloader downloader = new KaoImageDownloader(imagePath);
                String fileName= url.substring(url.lastIndexOf("/"));
                downloader.downloadByUrl(url,fileName);
                imageList.add(fileName);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });



    }

}
