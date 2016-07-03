package mti.nxt.kao.collector;


import twitter4j.*;

/**
 * Created by kanamori_m on 2016/07/03.
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Start kao collector");

        Twitter twitter = TwitterFactory.getSingleton();

        try {
            // ユーザーの表示名からユーザーIDの取得
            User user = twitter.showUser("@masaosaan");
            System.out.println(user.getId());
            final long id = user.getId();

            final ResponseList<Status> userTimeline = twitter.getUserTimeline(id);


            System.out.println(userTimeline.size());

            userTimeline.forEach(tweet -> {
                System.out.println("tweet:--------------");
                System.out.println(tweet.getText());
                System.out.println("--------------------");
                final MediaEntity[] mediaEntities = tweet.getMediaEntities();
                for (MediaEntity media:mediaEntities) {
                    System.out.println("Media=========");
                    System.out.println(media.getMediaURL());
                }
            });


        } catch (TwitterException e) {
            e.printStackTrace();
        }


    }

}
