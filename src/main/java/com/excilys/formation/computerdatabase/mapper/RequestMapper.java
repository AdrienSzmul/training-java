package com.excilys.formation.computerdatabase.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.servlets.constants.ColumnNames;

public class RequestMapper {
    private static final Logger logger = LoggerFactory
            .getLogger(RequestMapper.class);

    private RequestMapper() {
    }

    public static ColumnNames mapOrderBy(HttpServletRequest request,
            String orderby, ColumnNames orderbyDefault) {
        String orderbyStr = request.getParameter(orderby);
        for (ColumnNames colname : ColumnNames.values()) {
            if (colname.toString().equals(orderbyStr)) {
                return colname;
            }
        }
        return orderbyDefault;
    }

    public static Integer mapPageNumber(HttpServletRequest request, String size,
            Integer defaultPage) {
        String sizeStr = request.getParameter(size);
        if (!StringUtils.isBlank(sizeStr) && sizeStr.matches("[0-9]+")) {
            return Integer.parseInt(sizeStr);
        }
        return defaultPage;
    }

    public static PageLength mapPageSize(HttpServletRequest request,
            String size, PageLength defaultSize) throws PageLengthException {
        String sizeStr = request.getParameter(size);
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

    public static boolean mapAscDesc(HttpServletRequest request, String state,
            Boolean defaultState) {
        String stateStr = request.getParameter(state);
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
