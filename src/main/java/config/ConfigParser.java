package config;

import exception.ParseException;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取DataBase配置
 */
@Log
public class ConfigParser {
    public DBConfig parse(String path) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(path);
            Properties properties = new Properties();
            properties.load(in);
            int pageSize = Integer.parseInt(properties.getProperty("pageSize", "4096"));
            int bufferPoolCapacity = Integer.parseInt(properties.getProperty("bufferPoolCapacity", "100"));
            log.info("配置解析成功");
            return new DBConfig(pageSize, bufferPoolCapacity);
        } catch (IOException e) {
            log.info("配置解析失败");
            throw new ParseException("配置解析失败", e);
        }
    }
}
