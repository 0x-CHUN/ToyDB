package model.page;

import transaction.TransactionID;

import java.io.IOException;

/**
 * Page 抽象接口页，读写磁盘的基本单位
 */
public interface Page {
    /**
     * 获取Page的ID
     */
    PageID getPageID();

    /**
     * 序列化成字节流
     */
    byte[] serialize() throws IOException;

    /**
     * 反序列化成Page
     */
    Page deserialize(byte[] data) throws IOException;

    /**
     * 返回每页可存放的行数
     */
    int getMaxSlotNum();

    boolean isSlotUsed(int index);

    boolean hasEmptySlot();

    /**
     * 标记是否为脏页
     */
    void markDirty(TransactionID transactionID);

    boolean isDirty();

    TransactionID getDirtyTxId();

    /**
     * 保留页原始数据
     */
    void saveBeforePage();

    /**
     * 获取页在修改前的数据
     */
    Page getBeforePage();
}
