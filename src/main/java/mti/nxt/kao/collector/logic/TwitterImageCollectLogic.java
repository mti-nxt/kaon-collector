package mti.nxt.kao.collector.logic;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class TwitterImageCollectLogic {

    // TODO 対象ユーザの渡し方
    String userName = "@masaosaan";

    public void execute() {

        final List<String> mediaURLList = getMediaURLList(userName);

    }

    private List<String> getMediaURLList(String userName) {
        Twitter twitter = TwitterFactory.getSingleton();

        try {
            // ユーザーの表示名からユーザーIDの取得
            User user = twitter.showUser(userName);
            System.out.println(user.getId());
            final long id = user.getId();

            final ResponseList<Status> userTimeline = twitter.getUserTimeline(id);


            System.out.println(userTimeline.size());

            List<String> mediaURLList = new ArrayList();

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

            return mediaURLList;


        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
