/**
 *
 */
package com.excilys.formation.computerdatabase.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.paginator.Page;
import com.excilys.formation.computerdatabase.paginator.PageCompany;
import com.excilys.formation.computerdatabase.paginator.PageComputer;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.DateMismatchException;
import com.excilys.formation.computerdatabase.service.MissingCompanyException;
import com.excilys.formation.computerdatabase.service.NullNameException;

/**
 * @author excilys
 */
public class CommandLineInterface {
    private String res;
    private final BufferedReader br;
    private final ComputerService computerService = ComputerService.INSTANCE;
    private static final int TAILLE_MAX = PageLength.TWENTY.getValue();
    private boolean gettingOutOfCDB = true;

    public CommandLineInterface() {
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
                .append("7) Quitter Computer DataBase\n").toString();
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
        if (!valeurEntree.isEmpty()) {
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

    private void delComputer() {
        System.out.println("Donnez l'id du PC \n");
        final String s = getLineInString();
        if (s != null) {
            final Long id = Long.parseLong(s);
            final int nombreResComputers = computerService
                    .getPageCountComputers(TAILLE_MAX);
            if (id < nombreResComputers + 1) {
                final ComputerBuilder builderDetailsComputer = new Computer.ComputerBuilder()
                        .withId(id);
                final Computer computerToService = builderDetailsComputer
                        .build();
                computerService.deleteComputer(computerToService);
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private void updateComputer() throws NullNameException,
            DateMismatchException, MissingCompanyException {
        System.out.println("Donnez l'id du PC \n");
        final String s = getLineInString();
        if (s != null) {
            final Long id = Long.parseLong(s);
            final int nombreResComputers = computerService
                    .getPageCountComputers(TAILLE_MAX);
            if (id < nombreResComputers + 1) {
                final ComputerBuilder builderDetailsComputer = getComputerInfosFromUser();
                builderDetailsComputer.withId(id);
                final Computer computerToService = builderDetailsComputer
                        .build();
                computerService.updateComputer(computerToService);
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private void createComputer() throws NullNameException,
            DateMismatchException, MissingCompanyException {
        final ComputerBuilder builderCreateComputer = getComputerInfosFromUser();
        final Computer computer = builderCreateComputer.build();
        computerService.createComputer(computer);
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
            final int nombreResComputers = computerService
                    .getPageCountComputers(TAILLE_MAX);
            if (id < nombreResComputers + 1) {
                final ComputerBuilder builderDetailsComputer = new Computer.ComputerBuilder()
                        .withId(id);
                final Computer computerToService = builderDetailsComputer
                        .build();
                final Computer computerFromService = computerService
                        .showDetails(computerToService);
                System.out.println(computerFromService.toString());
            } else {
                System.out.println(
                        "L'id que vous avez donné ne correspond à rien\n");
            }
        }
    }

    private <T extends Page<?>> void readPage(final T page) {
        String choice = "f";
        while (!choice.equals("q")) {
            switch (choice) {
                case "s":
                    page.nextPage().forEach(System.out::println);
                    break;
                case "p":
                    page.previousPage().forEach(System.out::println);
                    break;
                case "f":
                    page.firstPage().forEach(System.out::println);
                    break;
                case "d":
                    page.lastPage().forEach(System.out::println);
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
        readPage(new PageCompany());
    }

    private void getListComputers() {
        readPage(new PageComputer());
    }

    private String getLineInString() {
        String s = null;
        try {
            s = br.readLine();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        // TODO Auto-generated method stub
        final CommandLineInterface cli = new CommandLineInterface();
        while (cli.gettingOutOfCDB) {
            cli.mainLoop();
        }
        cli.br.close();
    }
}
