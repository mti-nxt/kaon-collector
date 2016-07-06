package mti.nxt.kao.collector.image;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by masakkuma on 2016/07/05.
 */
public class KaoImageDownloaderTest {

    /**
     * 処理の流れ
     * ファイルをダウンロードする→年月日時分秒
     * s3に配置する
     * ダウンロードしたファイルを削除する
     * 終了
     *
     * TwottterIdに関連付けてデータ作る必要がある
     *
     */

    /**
     *
     */
    @Test
    public void imageDownloadTest() throws IOException {
        final KaoImageDownloader downloader = new KaoImageDownloader(Paths.get("/var/kaotest/"));
        downloader.downloadByUrl("http://pbs.twimg.com/media/ClfI5ANUkAASwrQ.jpg");

        //TODO ダウンロードしたファイルの検証
        final Path downloadDir = Paths.get("kaotest");
        try {
            final long downloadFiles = Files.list(downloadDir).count();
            assertThat(downloadFiles,is(1));
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException");
        }

    }

    @After
    public void doImagesClean() {
        try {
            Files.list(Paths.get("kaotest")).forEach(file -> {
               try {
                   Files.deleteIfExists(file);
               }catch (IOException e) {
                   e.printStackTrace();
               }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}