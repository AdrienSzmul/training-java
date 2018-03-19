/**
 * 
 */
package com.excilys.formation.computerdatabase.ui;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.DateMismatchException;
import com.excilys.formation.computerdatabase.service.MissingCompanyException;
import com.excilys.formation.computerdatabase.service.NullNameException;

/**
 * @author excilys
 *
 */

public class CommandLineInterface {

	private String res;
	private BufferedReader br;
	private ComputerService computerService = ComputerService.INSTANCE;
	private CompanyService companyService = CompanyService.INSTANCE;
	private int taillePage;
	private boolean gettingOutOfCDB = true;

	public CommandLineInterface() {
		br = new BufferedReader(new InputStreamReader(System.in));
		taillePage = 20;
	}

	public String menuCLI() {
		StringBuilder strb = new StringBuilder();
		res = strb.append("******************************\n").append("Que souhaitez-vous effectuer ?\n\n")
				.append("******************************\n\n").append("1) Lister les ordinateurs\n")
				.append("2) Lister les entreprises\n").append("3) Obtenir le détail d'un ordinateur\n")
				.append("4) Ajouter un ordinateur\n").append("5) Mettre à jour un ordinateur\n")
				.append("6) Supprimer un ordinateur\n").append("7) Quitter Computer DataBase\n").toString();
		return res;
	}

	/**
	 * @param cli
	 * @param s
	 * @throws MissingCompanyException
	 * @throws DateMismatchException
	 * @throws NullNameException
	 */
	public void mainLoop() throws NullNameException, DateMismatchException, MissingCompanyException {
		System.out.println(menuCLI());
		String valeurEntree = getLineInString();
		if (!valeurEntree.isEmpty()) {
			int s = Integer.parseInt(valeurEntree) - 1;
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
				System.out.println("ComputerDataBase est en train de fermer...");
				gettingOutOfCDB = false;
				break;
			default:
				throw new ArrayIndexOutOfBoundsException("Votre chiffre ne correspond à rien :D \n");
			}
		} else {
			System.out.println("Veuillez entrer un chiffre");
		}
	}

	private void delComputer() {
		System.out.println("Donnez l'id du PC \n");
		String s = getLineInString();
		if (s != null) {
			Long id = Long.parseLong(s);
			int nombreResComputers = computerService.getPageCountComputers(taillePage);
			if (id < nombreResComputers + 1) {
				ComputerBuilder builderDetailsComputer = new Computer.ComputerBuilder().withId(id);
				Computer computerToService = builderDetailsComputer.build();
				computerService.deleteComputer(computerToService);
			} else {
				System.out.println("L'id que vous avez donné ne correspond à rien\n");
			}
		}
	}

	private void updateComputer() throws NullNameException, DateMismatchException, MissingCompanyException {
		System.out.println("Donnez l'id du PC \n");
		String s = getLineInString();
		if (s != null) {
			Long id = Long.parseLong(s);
			int nombreResComputers = computerService.getPageCountComputers(taillePage);
			if (id < nombreResComputers + 1) {
				ComputerBuilder builderDetailsComputer = getComputerInfosFromUser();
				builderDetailsComputer.withId(id);
				Computer computerToService = builderDetailsComputer.build();
				computerService.updateComputer(computerToService);
			} else {
				System.out.println("L'id que vous avez donné ne correspond à rien\n");
			}
		}
	}

	private void createComputer() throws NullNameException, DateMismatchException, MissingCompanyException {
		ComputerBuilder builderCreateComputer = getComputerInfosFromUser();
		Computer computer = builderCreateComputer.build();
		computerService.createComputer(computer);
	}

	private ComputerBuilder getComputerInfosFromUser() {
		System.out.println("Donnez le nouveau nom du PC\n");
		String name = getLineInString();
		System.out.println("Donnez la date d'introduction (format yyyy-mm/-d)\n");
		String date_introduced = getLineInString();
		LocalDate introduced = LocalDate.parse(date_introduced);
		System.out.println("Donnez la date d'introduction (format yyyy-mm/-d)\n");
		String date_discontinued = getLineInString();
		LocalDate discontinued = LocalDate.parse(date_discontinued);
		System.out.println("Donnez l'id de l'entreprise liée\n");
		String company_identifier = getLineInString();
		Long company_id = Long.parseLong(company_identifier);
		CompanyBuilder builderCreateCompany = new Company.CompanyBuilder().withId(company_id);
		Company company = builderCreateCompany.build();
		ComputerBuilder builderCreateComputer = new Computer.ComputerBuilder().withName(name).withIntroduced(introduced)
				.withDiscontinued(discontinued).withCompany(company);
		return builderCreateComputer;
	}

	private void showdetails() {
		System.out.println("Donnez l'id du PC ");
		String s = getLineInString();
		if (s != null) {
			Long id = Long.parseLong(s);
			int nombreResComputers = computerService.getPageCountComputers(taillePage);
			if (id < nombreResComputers + 1) {
				ComputerBuilder builderDetailsComputer = new Computer.ComputerBuilder().withId(id);
				Computer computerToService = builderDetailsComputer.build();
				Computer computerFromService = computerService.showDetails(computerToService);
				System.out.println(computerFromService.toString());
			} else {
				System.out.println("L'id que vous avez donné ne correspond à rien\n");
			}
		}

	}

	private void getListCompanies() {
		int nombreResCompanies = companyService.getPageCountCompanies(taillePage);
		for (int j = 0; j < nombreResCompanies + 1; j++) {
			List<Company> listCompanies = companyService.getListCompanies(j, taillePage);
			listCompanies.forEach(company -> System.out.println(company.toString()));
			getLine();
		}
	}

	private void getListComputers() {
		int nombreResComputers = computerService.getPageCountComputers(taillePage);
		for (int j = 0; j < nombreResComputers + 1; j++) {
			List<Computer> listComputers = computerService.getListComputers(j, taillePage);
			listComputers.forEach(computer -> System.out.println(computer.toString()));
			getLine();
		}

	}

	/**
	 * 
	 */
	private void getLine() {
		try {
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getLineInString() {
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
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
	public static void main(String[] args)
			throws IOException, NullNameException, DateMismatchException, MissingCompanyException {
		// TODO Auto-generated method stub
		CommandLineInterface cli = new CommandLineInterface();
		while (cli.gettingOutOfCDB) {
			cli.mainLoop();
		}
		cli.br.close();
	}

}