package model.field;

import operator.PredicateEnum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class IntField implements Field, Serializable {
    private final int value;

    public IntField(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public FieldType getType() {
        return FieldType.INT;
    }

    @Override
    public void serialize(DataOutputStream out) throws IOException {
        out.writeInt(this.value);
    }

    @Override
    public boolean compare(PredicateEnum predicateEnum, Field operand) {
        IntField operandValue = (IntField) operand;
        switch (predicateEnum) {
            case EQUALS -> {
                return value == operandValue.value;
            }
            case NOT_EQUALS -> {
                return value != operandValue.value;
            }
            case GREATER_THAN -> {
                return value > operandValue.value;
            }
            case GREATER_THAN_OR_EQ -> {
                return value >= operandValue.value;
            }
            case LESS_THAN -> {
                return value < operandValue.value;
            }
            case LESS_THAN_OR_EQ -> {
                return value <= operandValue.value;
            }
        }
        return false;
    }
}
