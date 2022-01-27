package config;

import junit.framework.TestCase;

public class ConfigParserTest extends TestCase {

    public void testParse() {
        ConfigParser parser = new ConfigParser();
        DBConfig config = parser.parse("db.properties");
        assertEquals(config.getPageSize(), 4096);
        assertEquals(config.getBufferPoolCapacity(), 100);
    }
}