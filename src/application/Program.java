package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Entre os dados do contrato: ");
		System.out.print("Numero: ");
		Integer number = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Data (dd/MM/yyyy): ");
		Date date = null;
		
		try {
			date = sdf.parse(sc.nextLine());
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.print("Valor do contrato: ");
		Double valueContract = sc.nextDouble();
		
		Contract contrato = new Contract(number, date, valueContract);
		ContractService servico = new ContractService();
		servico.setOnlinePaymentService(new PaypalService());
		
		System.out.print("Entre com o numero de parcelas: ");
		Integer parcelas = sc.nextInt();
		

		servico.processContract(contrato, parcelas);
		
		System.out.println("Parcelas: ");
		for(Installment parcela : contrato.getInstallments()) {
			System.out.println(parcela);
		}
		
		
		sc.close();
	}

}
