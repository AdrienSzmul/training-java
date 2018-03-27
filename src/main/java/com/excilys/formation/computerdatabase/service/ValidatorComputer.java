/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;
import com.excilys.formation.computerdatabase.persistence.dao.DAOException;

/**
 * @author excilys
 */
public enum ValidatorComputer {
    INSTANCE;
    private final CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    public void validateComputer(final Computer c) throws ValidationException {
        if (c.getName() == null) {
            throw new NullNameException(
                    "Le nom de votre PC ne peut être nul !");
        }
        if (c.getDiscontinued() != null && c.getIntroduced() != null) {
            if (c.getDiscontinued().isBefore(c.getIntroduced())) {
                throw new DateMismatchException(
                        "La date discontinued ne peut être avant ou égal à la date introduced");
            }
        }
        if (c.getCompany().getId() != null) {
            final CompanyBuilder b = new Company.CompanyBuilder();
            b.withId(c.getCompany().getId());
            try {
                if (companyDAO.showDetails(b.build()) == null) {
                    throw new MissingCompanyException(
                            "L'id de company que vous avez donné n'existe pas !");
                }
            } catch (DAOException e) {
                throw new ValidationException(e.getMessage());
            }
        }
    }
}
