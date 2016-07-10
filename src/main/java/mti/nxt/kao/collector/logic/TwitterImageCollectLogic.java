package mti.nxt.kao.collector.logic;

import mti.nxt.kao.collector.config.KaocollectorConfig;
import mti.nxt.kao.collector.image.KaoImageDownloader;
import mti.nxt.kao.collector.s3.S3Uploader;
import mti.nxt.kao.collector.twitter.TwitterClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class TwitterImageCollectLogic {

    String userName = KaocollectorConfig.get("kao.username");
    String imagePath =  KaocollectorConfig.get("kao.tmppath") +userName.substring(2) + "/";

    /**
     * 対象のアカウントのtweetから画像を集めてs3バケットにアップする
     */
    public void execute() {

        final TwitterClient twitterClient = new TwitterClient();
        final List<String> mediaURLList = twitterClient.getMediaURLList(userName);;

        mediaURLList.forEach(url -> {
            try {
                KaoImageDownloader downloader = new KaoImageDownloader(imagePath);
                String fileName= url.substring(url.lastIndexOf("/"));
                downloader.downloadByUrl(url,fileName);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        S3Uploader uploader = new S3Uploader(userName);
        try {
            uploader.upload(imagePath);
        } catch (IOException e) {
            System.out.println("S3 upload faild");
            e.printStackTrace();
        }


    }

}
