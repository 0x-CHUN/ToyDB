package model;

import config.ConfigParser;
import config.DBConfig;
import exception.DBException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import model.table.DBTable;
import model.table.TableDesc;
import model.table.tablefile.TableFile;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 数据库对象
 */
@Log
public class DataBase {
    // 单例
    private static final AtomicReference<DataBase> singleton = new AtomicReference<>();

    /**
     * 数据库配置
     */
    private final DBConfig dbConfig;

    private final HashMap<Integer, DBTable> tableID2Table;
    private final HashMap<String, DBTable> tableName2Table;


    private DataBase() {
        this.dbConfig = new ConfigParser().parse("db.properties");
        this.tableID2Table = new HashMap<>();
        this.tableName2Table = new HashMap<>();
    }

    public static DataBase getInstance() {
        return singleton.get();
    }

    /**
     * 添加新表
     *
     * @param tableFile 表文件
     * @param tableName 表名字
     * @param tableDesc 表结构
     */
    public void addTable(TableFile tableFile, String tableName, TableDesc tableDesc) {
        DBTable dbTable = new DBTable(tableName, tableFile, tableDesc);
        tableID2Table.put(tableFile.getTableID(), dbTable);
        tableName2Table.put(tableName, dbTable);
    }

    /**
     * 添加新表
     *
     * @param tableFile 表文件
     * @param tableName 表名字
     */
    public void addTable(TableFile tableFile, String tableName) {
        TableDesc tableDesc = tableFile.getTableDesc();
        if (tableDesc == null) {
            throw new DBException("table file 的表结构为空！");
        }
        DBTable dbTable = new DBTable(tableName, tableFile, tableDesc);
        tableID2Table.put(tableFile.getTableID(), dbTable);
        tableName2Table.put(tableName, dbTable);
    }

    /**
     * 根据tableID返回DBTable
     */
    public DBTable getDBTableByID(int tableID) {
        DBTable dbTable = tableID2Table.get(tableID);
        if (dbTable == null) {
            throw new DBException("table 不存在");
        }
        return dbTable;
    }

    /**
     * 根据tableName返回DBTable
     */
    public DBTable getDBTableByName(String name) {
        DBTable dbTable = tableName2Table.get(name);
        if (dbTable == null) {
            throw new DBException("table 不存在");
        }
        return dbTable;
    }
}
