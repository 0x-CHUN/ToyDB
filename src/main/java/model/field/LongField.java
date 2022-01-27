package model.field;

import operator.PredicateEnum;

import java.io.DataOutputStream;
import java.io.IOException;

public class LongField implements Field {
    private final long value;

    public LongField(long value) {
        this.value = value;
    }

    @Override
    public FieldType getType() {
        return FieldType.LONG;
    }

    @Override
    public void serialize(DataOutputStream out) throws IOException {
        out.writeLong(this.value);
    }

    @Override
    public boolean compare(PredicateEnum predicateEnum, Field operand) {
        LongField operandValue = (LongField) operand;
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
