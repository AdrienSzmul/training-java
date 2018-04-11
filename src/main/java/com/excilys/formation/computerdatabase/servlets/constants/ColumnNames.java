package com.excilys.formation.computerdatabase.servlets.constants;

public enum ColumnNames {
    NAME("cu_name"), INTRODUCED("cu_introduced"), DISCONTINUED(
            "cu_discontinued"), COMPANY("ca_name");
    private final String value;

    ColumnNames(String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
