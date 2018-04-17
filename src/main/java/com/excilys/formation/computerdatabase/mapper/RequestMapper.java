package com.excilys.formation.computerdatabase.mapper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.paginator.PageLength;

public class RequestMapper {
    private static final Logger logger = LoggerFactory
            .getLogger(RequestMapper.class);

    private RequestMapper() {
    }

    public static ColumnNames mapOrderBy(Map<String, String> allParams,
            String orderby, ColumnNames orderbyDefault) {
        String orderbyStr = allParams.get("orderby");
        for (ColumnNames colname : ColumnNames.values()) {
            if (colname.toString().equals(orderbyStr)) {
                return colname;
            }
        }
        return orderbyDefault;
    }

    public static Integer mapPageNumber(Map<String, String> allParams,
            String size, Integer defaultPage) {
        String sizeStr = allParams.get("size");
        if (!StringUtils.isBlank(sizeStr) && sizeStr.matches("[0-9]+")) {
            return Integer.parseInt(sizeStr);
        }
        return defaultPage;
    }

    public static PageLength mapPageSize(Map<String, String> allParams,
            String size, PageLength defaultSize) throws PageLengthException {
        String sizeStr = allParams.get("size");
        if (!StringUtils.isBlank(sizeStr) && sizeStr.matches("[0-9]+")) {
            Integer tmpSize = Integer.parseInt(sizeStr);
            try {
                return PageLengthMapper.toTailleMax(tmpSize);
            } catch (IllegalArgumentException e) {
                logger.debug(e.getMessage());
            }
        }
        return defaultSize;
    }

    public static boolean mapAscDesc(Map<String, String> allParams,
            String state, Boolean defaultState) {
        String stateStr = allParams.get("state");
        if (!StringUtils.isBlank(stateStr)) {
            if (stateStr.equals("true")) {
                return true;
            }
            if (stateStr.equals("false")) {
                return false;
            }
        }
        return defaultState;
    }
}
