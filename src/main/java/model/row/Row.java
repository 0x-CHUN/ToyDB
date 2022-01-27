package model.row;

import lombok.Data;
import model.field.Field;
import model.table.TableDesc;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 元组，表中的一行
 */
@Data
public class Row implements Serializable {
    @Serial
    private static final long serialVersionUID = 3508867799019762862L;

    /**
     * 行中的字段
     */
    private Field[] fields;
    /**
     * 表的描述信息
     */
    private TableDesc tableDesc;
    /**
     * rowID
     */
    private RowID rowID;

    public Row(TableDesc tableDesc, RowID rowID) {
        this.tableDesc = tableDesc;
        this.rowID = rowID;
    }

    public Row(TableDesc tableDesc) {
        this.tableDesc = tableDesc;
        this.fields = new Field[tableDesc.getAttributesNum()];
    }

    /**
     * 合并两个表中一行
     *
     * @param leftRow  左表中的一行
     * @param rightRow 右表中的一行
     * @return 合并后的一行
     */
    public static Row merge(Row leftRow, Row rightRow) {
        Row mergedRow = new Row(TableDesc.merge(leftRow.getTableDesc(), rightRow.getTableDesc()));
        List<Field> leftFields = leftRow.getFields();
        List<Field> rightFields = rightRow.getFields();
        List<Field> mergedFields = new ArrayList<>();
        mergedFields.addAll(leftFields);
        mergedFields.addAll(rightFields);
        mergedRow.setFields(mergedFields.toArray(new Field[0]));
        return mergedRow;
    }

    public List<Field> getFields() {
        return Stream.of(fields).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder column = new StringBuilder().append("[");
        for (int i = 0; i < fields.length - 1; i++) {
            column.append(fields[i].toString()).append("\t");
        }
        column.append(fields[fields.length - 1]).append("]");
        return column.toString();
    }
}
