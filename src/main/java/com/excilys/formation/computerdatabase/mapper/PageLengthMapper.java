package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.paginator.PageLength;

public class PageLengthMapper {
    public PageLengthMapper() {
    }

    public static PageLength toTailleMax(Integer i) throws PageLengthException {
        for (PageLength pl : PageLength.values()) {
            if (i.equals(pl.getValue())) {
                return pl;
            }
        }
        throw new PageLengthException(
                "La valeur de longueur ne correspond à aucun prévue !");
    }
}
