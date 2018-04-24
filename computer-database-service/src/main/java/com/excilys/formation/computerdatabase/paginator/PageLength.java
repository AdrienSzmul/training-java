package com.excilys.formation.computerdatabase.paginator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PageLength {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);
    private int value;

    PageLength(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public static List<Integer> toIntList() {
        List<Integer> intList = new ArrayList<>(PageLength.values().length);
        Arrays.stream(PageLength.values())
                .forEach(i -> intList.add(i.getValue()));
        Collections.sort(intList);
        return intList;
    }
}
