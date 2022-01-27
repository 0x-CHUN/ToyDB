package model.table;

import exception.DBException;
import lombok.Data;
import lombok.extern.java.Log;
import model.row.Row;
import model.table.tablefile.TableFile;

import java.io.IOException;
import java.util.Objects;

/**
 * 表
 */
@Data
@Log
public class DBTable {
    /**
     * 表名
     */
    private final String tableName;
    /**
     * 表ID
     */
    private final int tableId;
    /**
     * 表结构
     */
    private final TableDesc tableDesc;

    /**
     * 表的存储文件
     */
    private final TableFile tableFile;

    public DBTable(String tableName, TableFile tableFile, TableDesc tableDesc) {
        this.tableName = tableName;
        this.tableFile = tableFile;
        this.tableDesc = tableDesc;
        this.tableId = tableFile.getTableID();
    }

    /**
     * 插入一行
     */
    public void insertRow(Row row) throws IOException {
        if (row == null) {
            throw new DBException("insertRow error: row can not be null");
        }
        if (!Objects.equals(row.getTableDesc(), this.getTableDesc())) {
            throw new DBException("insertRow error: TableDesc not match");
        }
        if (row.getFields() == null || row.getFields().isEmpty()) {
            throw new DBException("insertRow error: have no fields value");
        }
        if (row.getFields().contains(null)) {
            throw new DBException("insertRow error: 'null' field is not support");
        }
        log.info("Insert row " + row);
        this.tableFile.insertRow(row);
    }

    public void deleteRow(Row row) throws IOException {
        if (!Objects.equals(row.getTableDesc(), this.getTableDesc())) {
            throw new DBException("insertRow error: TableDesc not match");
        }
        log.info("Delete " + row);
        this.tableFile.deleteRow(row);
    }
}
