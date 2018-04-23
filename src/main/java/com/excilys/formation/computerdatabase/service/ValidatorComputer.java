/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import org.springframework.stereotype.Component;

import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author excilys
 */
@Component
public class ValidatorComputer {
    private CompanyService companyService;

    public ValidatorComputer(CompanyService companyService) {
        this.companyService = companyService;
    }

    public void validateComputer(final Computer c) throws ValidationException {
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
                System.out.println(
                        companyService.getCompanyById(c.getCompany().getId()));
                if (c.getCompany().getId() != null && companyService
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
