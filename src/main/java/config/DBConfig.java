package config;

import lombok.Data;

/**
 * 数据库配置
 */
@Data
public class DBConfig {
    /**
     * 页大小
     */
    private final int pageSize;

    /**
     * 缓冲池大小
     */
    private final int bufferPoolCapacity;
}
