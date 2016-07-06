package mti.nxt.kao.collector.twitter;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class TwitterClient {

    private Twitter twitter = TwitterFactory.getSingleton();

    public List<String> getMediaURLList(String userName) {
        List<String> mediaURLList = new ArrayList();

        try {
            // ユーザーの表示名からユーザーIDの取得
            User user = twitter.showUser(userName);
            System.out.println(user.getId());
            final long id = user.getId();

            final ResponseList<Status> userTimeline = twitter.getUserTimeline(id);


            System.out.println(userTimeline.size());


            userTimeline.forEach(tweet -> {
                System.out.println("tweet:--------------");
                System.out.println(tweet.getText());
                System.out.println("--------------------");
                final MediaEntity[] mediaEntities = tweet.getMediaEntities();
                for (MediaEntity media : mediaEntities) {
                    System.out.println("Media=========");
                    System.out.println(media.getMediaURL());
                    mediaURLList.add(media.getMediaURL());
                }
            });
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return mediaURLList;
    }
}
