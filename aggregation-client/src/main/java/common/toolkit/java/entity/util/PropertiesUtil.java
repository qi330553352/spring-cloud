package common.toolkit.java.entity.util;

import java.io.*;
import java.util.Properties;

/**
 * @author nileader / nileader@gmail.com
 * @Date 2012-4-11
 */
public class PropertiesUtil {
    private static Properties properties = null;

    /**
     * 初始加载配置文件
     * @param fileName
     */
    public static void initLoad(String fileName) {
        try {
            if (fileName != null && "".equals(fileName) == false) {
                InputStream in = new BufferedInputStream(new FileInputStream(fileName));
                properties = new Properties();
                properties.load(in);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据KEY读取配置文件
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        if (key == null || "".equals(key)) {
            return null;
        }

        if (properties == null) {
            return null;
        }

        return properties.getProperty(key);
    }
}
