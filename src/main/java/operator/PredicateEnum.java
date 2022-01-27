package operator;

import java.util.Arrays;
import java.util.List;

/**
 * predicate谓词，用于filter、join
 */
public enum PredicateEnum {
    EQUALS,
    GREATER_THAN,
    LESS_THAN,
    LESS_THAN_OR_EQ,
    GREATER_THAN_OR_EQ,
    NOT_EQUALS,
    LIKE,
    LEFT_LIKE,
    RIGHT_LIKE
}
