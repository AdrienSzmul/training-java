/**
 *
 */
package com.excilys.formation.computerdatabase.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.paginator.Page;
import com.excilys.formation.computerdatabase.paginator.PageCompany;
import com.excilys.formation.computerdatabase.paginator.PageComputer;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.DateMismatchException;
import com.excilys.formation.computerdatabase.service.MissingCompanyException;
import com.excilys.formation.computerdatabase.service.NullNameException;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.service.ValidationException;

/**
 * @author excilys
 */
@Controller
public class CommandLineInterface {
    private String res;
    private final BufferedReader br;
    private ComputerService computerService;
    private CompanyService companyService;
    private static final int TAILLE_MAX = PageLength.TWENTY.getValue();
    private boolean gettingOutOfCDB = true;
    private final Logger logger = LoggerFactory
            .getLogger(CommandLineInterface.class);

    public CommandLineInterface(ComputerService computerService,
            CompanyService companyService) {
        this.computerService = computerService;
        this.companyService = companyService;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public final String menuCLI() {
        final StringBuilder strb = new StringBuilder();
        res = strb.append("******************************\n")
                .append("Que souhaitez-vous effectuer ?\n\n")
                .append("******************************\n\n")
                .append("1) Lister les ordinateurs\n")
                .append("2) Lister les entreprises\n")
                .append("3) Obtenir le détail d'un ordinateur\n")
                .append("4) Ajouter un ordinateur\n")
                .append("5) Mettre à jour un ordinateur\n")
                .append("6) Supprimer un ordinateur\n")
                .append("7) SUpprimer une compagnie\n")
                .append("8) Quitter Computer DataBase\n").toString();
        return res;
    }

    /**
     * @param cli
     * @param s
     * @throws MissingCompanyException
     * @throws DateMismatchException
     * @throws NullNameException
     */
    public final void mainLoop() throws NullNameException,
            DateMismatchException, MissingCompanyException {
        System.out.println(menuCLI());
        final String valeurEntree = getLineInString();
        if (!StringUtils.isBlank(valeurEntree)) {
            final int s = Integer.parseInt(valeurEntree) - 1;
            switch (MenuChoice.values()[s]) {
                case SELECT_LIST_COMPUTERS:
                    getListComputers();
                    break;
                case SELECT_LIST_COMPANIES:
                    getListCompanies();
                    break;
                case SELECT_ONE_COMPUTER:
                    showdetails();
                    break;
                case INSERT_NEW_COMPUTER:
                    createComputer();
                    break;
                case UPDATE_EXISTING_COMPUTER:
                    updateComputer();
                    break;
                case DELETE_EXISTING_COMPUTER:
                    delComputer();
                    break;
                case DELETE_EXISTING_COMPANY:
                    delCompany();
                    break;
                case QUIT:
                    System.out.println(
                            "ComputerDataBase est en train de fermer...");
                    gettingOutOfCDB = false;
                    break;
                default:
                    throw new ArrayIndexOutOfBoundsException(
                            "Votre chiffre ne correspond à rien :D \n");
            }
        } else {
            System.out.println("Veuillez entrer un chiffre");
        }
    }

    private void delCompany() {
        System.out.println("Donnez l'id de la compagnie \n");
        final String s = getLineInString();
        if (!StringUtils.isBlank(s)) {
            final Long id = Long.parseLong(s);
            int nombreResCompanies = 0;
            try {
                nombreResCompanies = companyService.getCountCompanies();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
            if (id < nombreResCompanies + 1) {
                try {
                    Company company = companyService.getCompanyById(id);
                    companyService.deleteCompany(company);
                } catch (ServiceException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private void delComputer() {
        System.out.println("Donnez l'id du PC \n");
        final String s = getLineInString();
        if (s != null) {
            final Long id = Long.parseLong(s);
            int nombreResComputers = 0;
            try {
                nombreResComputers = computerService.getCountComputers();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
            if (id < nombreResComputers + 1) {
                try {
                    Computer computer = computerService.getComputerById(id);
                    computerService.deleteComputer(computer);
                } catch (ServiceException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private void updateComputer() {
        System.out.println("Donnez l'id du PC \n");
        final String s = getLineInString();
        if (s != null) {
            final Long id = Long.parseLong(s);
            int nombreResComputers = 0;
            try {
                nombreResComputers = computerService
                        .getPageCountComputers(TAILLE_MAX);
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
            if (id < nombreResComputers + 1) {
                try {
                    Computer computer = computerService.getComputerById(id);
                    computerService.updateComputer(computer);
                } catch (ValidationException | ServiceException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private void createComputer() {
        final ComputerBuilder builderCreateComputer = getComputerInfosFromUser();
        final Computer computer = builderCreateComputer.build();
        try {
            computerService.createComputer(computer);
        } catch (ValidationException | ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private ComputerBuilder getComputerInfosFromUser() {
        System.out.println("Donnez le nouveau nom du PC\n");
        final String name = getLineInString();
        System.out
                .println("Donnez la date d'introduction (format yyyy-mm-dd)\n");
        final String dateIntroduced = getLineInString();
        final LocalDate introduced = LocalDate.parse(dateIntroduced);
        System.out
                .println("Donnez la date d'introduction (format yyyy-mm-dd)\n");
        final String dateDiscontinued = getLineInString();
        final LocalDate discontinued = LocalDate.parse(dateDiscontinued);
        System.out.println("Donnez l'id de l'entreprise liée\n");
        final String companyIdentifier = getLineInString();
        final Long companyId = Long.parseLong(companyIdentifier);
        final CompanyBuilder builderCreateCompany = new Company.CompanyBuilder()
                .withId(companyId);
        final Company company = builderCreateCompany.build();
        final ComputerBuilder builderCreateComputer = new Computer.ComputerBuilder()
                .withName(name).withIntroduced(introduced)
                .withDiscontinued(discontinued).withCompany(company);
        return builderCreateComputer;
    }

    private void showdetails() {
        System.out.println("Donnez l'id du PC ");
        final String s = getLineInString();
        if (s != null) {
            final Long id = Long.parseLong(s);
            int nombreResComputers = 0;
            try {
                nombreResComputers = computerService
                        .getPageCountComputers(TAILLE_MAX);
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
            if (id < nombreResComputers + 1) {
                Computer computer = null;
                try {
                    computer = computerService.getComputerById(id);
                } catch (ServiceException e) {
                    System.out.println(e.getMessage());
                }
                if (!StringUtils.isBlank(computer.toString())) {
                    System.out.println(computer.toString());
                }
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private <T extends Page<?>> void readPage(final T page) {
        String choice = "f";
        while (!StringUtils.isBlank(choice) && !choice.equals("q")) {
            switch (choice) {
                case "s":
                    try {
                        page.nextPage().forEach(System.out::println);
                    } catch (ServiceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "p":
                    try {
                        page.previousPage().forEach(System.out::println);
                    } catch (ServiceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "f":
                    try {
                        page.firstPage().forEach(System.out::println);
                    } catch (ServiceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "d":
                    try {
                        page.lastPage().forEach(System.out::println);
                    } catch (ServiceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "q":
                    System.out.println("Closing...");
                default:
            }
            System.out.println(
                    "s pour suivant, p pour précédent, f pour premier, d pour dernier, q pour quitter");
            choice = getLineInString();
        }
    }

    private void getListCompanies() {
        readPage(new PageCompany(companyService));
    }

    private void getListComputers() {
        readPage(new PageComputer(computerService));
    }

    private String getLineInString() {
        String s = "";
        try {
            s = br.readLine();
        } catch (final IOException e) {
            logger.debug(e.getMessage());
        }
        return s;
    }

    /**
     * @param args
     * @throws IOException
     * @throws MissingCompanyException
     * @throws DateMismatchException
     * @throws NullNameException
     */
    public static void main(final String[] args) throws IOException,
            NullNameException, DateMismatchException, MissingCompanyException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "application-context.xml");
        final CommandLineInterface cli = context
                .getBean(CommandLineInterface.class);
        while (cli.gettingOutOfCDB) {
            cli.mainLoop();
        }
        cli.br.close();
    }
}
