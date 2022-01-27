package model.table.tablefile;

import model.page.Page;
import model.page.PageID;
import model.row.Row;
import model.table.TableDesc;

import java.io.IOException;

/**
 * 表磁盘文件
 */
public interface TableFile {
    /**
     * 获取表结构
     */
    TableDesc getTableDesc();

    /**
     * 读取pageID的page
     *
     * @param pageID 需要读取的pageID
     */
    Page readPage(PageID pageID);

    /**
     * 将Page写入磁盘
     *
     * @param page 写入的Page
     */
    void writePage(Page page);

    /**
     * 返回文件的唯一ID(取文件路径散列值)
     */
    int getTableID();

    /**
     * 文件中Page数量
     */
    int getExistPageCount();

    /**
     * 插入行
     */
    void insertRow(Row row) throws IOException;

    /**
     * 删除行
     */
    void deleteRow(Row row) throws IOException;

    /**
     * 获取数据库的迭代器
     */
    TableFileIterator getIterator();
}
