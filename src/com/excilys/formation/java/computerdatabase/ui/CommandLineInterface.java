/**
 * 
 */
package com.excilys.formation.java.computerdatabase.ui;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.service.DateMismatchException;
import com.excilys.formation.java.computerdatabase.service.MissingCompanyException;
import com.excilys.formation.java.computerdatabase.service.NullNameException;

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
				.append("6) Supprimer un ordinateur").toString();
		return res;
	}

	/**
	 * @param cli
	 * @param s
	 * @throws MissingCompanyException
	 * @throws DateMismatchException
	 * @throws NullNameException
	 */
	public void mainLoop(String s) throws NullNameException, DateMismatchException, MissingCompanyException {
		switch (s) {
		case "1":
			getListComputers();

			break;
		case "2":
			getListCompanies();

			break;
		case "3":
			showdetails();

			break;
		case "4":
			createComputer();

			break;
		case "5":
			updateComputer();

			break;
		case "6":
			delComputer();

			break;

		default:
			throw new ArrayIndexOutOfBoundsException("Votre chiffre ne correspond à rien :D \n");
		}
	}

	private void delComputer() {
		System.out.println("Donnez l'id du PC \n");
		String s = getLineInString();
		if (s != null) {
			Long id = Long.parseLong(s);
			int nombreResComputers = computerService.getPageCountComputers(taillePage);
			if (id < nombreResComputers + 1) {
				computerService.deleteComputer(id);
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
				getInfosForUpdateAndCallService(id);
			} else {
				System.out.println("L'id que vous avez donné ne correspond à rien\n");
			}
		}
	}

	private void createComputer() throws NullNameException, DateMismatchException, MissingCompanyException {
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
		computerService.createComputer(name, introduced, discontinued, company_id);
	}

	private void showdetails() {
		System.out.println("Donnez l'id du PC ");
		String s = getLineInString();
		if (s != null) {
			Long id = Long.parseLong(s);
			int nombreResComputers = computerService.getPageCountComputers(taillePage);
			if (id < nombreResComputers + 1) {
				Computer computer = computerService.showDetails(id);
				System.out.println(computer.toString());
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
		String s = new String();
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * @param id
	 * @throws NullNameException
	 * @throws DateMismatchException
	 * @throws MissingCompanyException
	 */
	private void getInfosForUpdateAndCallService(Long id)
			throws NullNameException, DateMismatchException, MissingCompanyException {
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
		computerService.updateComputer(id, name, introduced, discontinued, company_id);
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
		while (true) {
			System.out.println(cli.menuCLI());
			String s = cli.getLineInString();
			cli.mainLoop(s);
		}
	}

}