package model.table.tablefile;

import exception.DBException;
import model.row.Row;

/**
 * 表迭代器
 */
public interface TableFileIterator {
    /**
     * 打开DB
     */
    void open() throws DBException;

    /**
     * 是否还有下一行
     */
    boolean hasNext() throws DBException;

    /**
     * 下一行
     */
    Row next() throws DBException;

    /**
     * 关闭DB
     */
    void close();
}
