package model.field;

import exception.ParseException;

import java.io.DataInputStream;
import java.io.IOException;

public enum FieldType implements IFieldType {
    INT() {
        @Override
        public int getSize() {
            return 4;
        }

        @Override
        public Field parse(DataInputStream in) {
            try {
                return new IntField(in.readInt());
            } catch (IOException e) {
                throw new ParseException("Parse field failed", e);
            }
        }
    },
    LONG() {
        @Override
        public int getSize() {
            return 8;
        }

        @Override
        public Field parse(DataInputStream in) {
            try {
                return new LongField(in.readLong());
            } catch (IOException e) {
                throw new ParseException("parse field failed", e);
            }
        }
    }
}
