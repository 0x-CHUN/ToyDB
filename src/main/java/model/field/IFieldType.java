package model.field;

import java.io.DataInputStream;

/**
 * FieldType的interface
 */
public interface IFieldType {
    /**
     * 返回字段的字节数
     */
    int getSize();

    /**
     * 从输入的字节流中解析出字段的值
     *
     * @param in 输入字节流
     */
    Field parse(DataInputStream in);
}
