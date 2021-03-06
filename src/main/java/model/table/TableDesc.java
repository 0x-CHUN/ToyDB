package model.table;

import lombok.Data;
import model.field.FieldType;
import model.field.IFieldType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 表的结构描述信息
 */
public class TableDesc implements Serializable {
    @Serial
    private static final long serialVersionUID = -1072772284817404997L;

    /**
     * 表中的属性
     */
    private final List<Attribute> attributes;

    public TableDesc(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public TableDesc(FieldType... fieldTypes) {
        this.attributes = Stream.of(fieldTypes).map(x -> new Attribute(null, x)).collect(Collectors.toList());
    }

    public int getAttributesNum() {
        return attributes.size();
    }

    public List<FieldType> getFieldTypes() {
        return this.attributes.stream().map(Attribute::getFieldType).collect(Collectors.toList());
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * 合并两个表结构
     *
     * @param left  左表
     * @param right 右表
     * @return 合并后的新表结构
     */
    public static TableDesc merge(TableDesc left, TableDesc right) {
        List<Attribute> leftAttributes = left.getAttributes();
        List<Attribute> rightAttributes = right.getAttributes();
        List<Attribute> mergedAttributes = new ArrayList<>();
        mergedAttributes.addAll(leftAttributes);
        mergedAttributes.addAll(rightAttributes);
        return new TableDesc(mergedAttributes);
    }

    /**
     * 获取表的一行的大小
     */
    public int getRowSize() {
        return this.attributes.stream()
                .map(Attribute::getFieldType)
                .mapToInt(IFieldType::getSize)
                .sum();
    }

    public FieldType getFieldType(int keyFieldIndex) {
        return this.attributes.get(keyFieldIndex).getFieldType();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == null ? this.attributes == null : ((TableDesc) obj).attributes.equals(this.attributes);
    }

    /**
     * 属性
     */
    public static class Attribute implements Serializable {
        @Serial
        private static final long serialVersionUID = 6568833840007706206L;
        /**
         * 字段名称
         */
        private final String fieldName;
        /**
         * 字段类型
         */
        private final FieldType fieldType;

        public Attribute(String fieldName, FieldType fieldType) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
        }

        public String getFieldName() {
            return fieldName;
        }

        public FieldType getFieldType() {
            return fieldType;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Attribute)) {
                return false;
            }

            return fieldType.equals(((Attribute) obj).fieldType)
                    && Objects.equals(fieldName, ((Attribute) obj).fieldName);
        }
    }
}
