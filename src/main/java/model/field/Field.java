package model.field;

import operator.PredicateEnum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * 字段
 */
public interface Field extends Serializable {
    /**
     * 返回字段数据类型
     */
    FieldType getType();

    void serialize(DataOutputStream out) throws IOException;

    String toString();

    /**
     * 是否满足 this.value 比较 operand
     *
     * @param predicateEnum 比较条件
     * @param operand       操作数
     * @return 是否满足 this.value 比较 operand
     */
    boolean compare(PredicateEnum predicateEnum, Field operand);
}
