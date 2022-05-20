package com.group4.rvv;

/**
 * An enum representing health department inspection grades.
 * @author ndars
 */
public enum Grade {
    A,
    B,
    C,
    NOT_YET_GRADED,
    GRADE_PENDING,
    GRADE_PENDING_ISSUED_ON_REOPENING,
    UNKNOWN;
    
    @Override
    public String toString() {
        switch(this) {
            case A: return "A";
            case B: return "B";
            case C: return "C";
            case NOT_YET_GRADED: return "Not yet graded";
            case GRADE_PENDING: return "Grade pending";
            case GRADE_PENDING_ISSUED_ON_REOPENING: return "Grade pending issued on reopening";
            case UNKNOWN: return "UNKNOWN";
            default: throw new IllegalArgumentException();
        }
    }
    
    public String inQueryFormat() {
        switch(this) {
            case A: return "A";
            case B: return "B";
            case C: return "C";
            case NOT_YET_GRADED: return "N";
            case GRADE_PENDING: return "Z";
            case GRADE_PENDING_ISSUED_ON_REOPENING: return "P";
//          case UNKNOWN: return "U"; this should literally never happen
            default: throw new IllegalArgumentException();
        }
    }
    
    public static Grade asEnum(String input) {
        switch(input) {
            case "A": return A;
            case "B": return B;
            case "C": return C;
            case "N": return NOT_YET_GRADED;
            case "Z": return GRADE_PENDING;
            case "P": return GRADE_PENDING_ISSUED_ON_REOPENING;
            default: return UNKNOWN;
        }
    }
}
