/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author excilys
 */
public enum ValidatorComputer {
    INSTANCE;
    public void validateComputer(final Computer c,
            CompanyService companyService) throws ValidationException {
        if (c.getName() == null) {
            throw new NullNameException(
                    "Le nom de votre PC ne peut être nul !");
        }
        if (c.getDiscontinued() != null && c.getIntroduced() != null
                && c.getDiscontinued().isBefore(c.getIntroduced())) {
            throw new DateMismatchException(
                    "La date discontinued ne peut être avant ou égal à la date introduced");
        }
        if (c.getCompany() != null) {
            try {
                if (c.getCompany().getId() != null || companyService
                        .getCompanyById(c.getCompany().getId()) == null) {
                    throw new MissingCompanyException(
                            "L'id de company que vous avez donné n'existe pas !");
                }
            } catch (ServiceException e) {
                throw new ValidationException(e.getMessage());
            }
        }
    }
}
