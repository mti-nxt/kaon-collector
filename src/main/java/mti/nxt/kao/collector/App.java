package mti.nxt.kao.collector;


import mti.nxt.kao.collector.logic.TwitterImageCollectLogic;

/**
 * Twitterのアカウント表示名のタイムライン上の画像ファイルをDLしてS3にアップロードする
 * Created by kanamori_m on 2016/07/03.
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Start kao collector");

        TwitterImageCollectLogic logic = new TwitterImageCollectLogic();
        logic.execute();


    }

}
