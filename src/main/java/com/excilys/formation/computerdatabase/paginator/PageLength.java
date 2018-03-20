package com.excilys.formation.computerdatabase.paginator;

public enum PageLength {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);
    private final int value;

    PageLength(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
