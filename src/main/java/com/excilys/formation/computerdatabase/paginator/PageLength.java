package com.excilys.formation.computerdatabase.paginator;

import java.util.HashMap;
import java.util.Map;

public enum PageLength {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);
    private final int value;
    private static final Map<Integer, PageLength> lookup = new HashMap<Integer, PageLength>();
    static {
        for (PageLength pl : PageLength.values()) {
            lookup.put(pl.getValue(), pl);
        }
    }

    PageLength(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
