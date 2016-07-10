package mti.nxt.kao.collector.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by masakkuma on 2016/07/11.
 */
public class KaocollectorConfig {

    private static Properties properties = new Properties();
    static {
        try (InputStream inputStream = new FileInputStream("kaocollector.properties")) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
