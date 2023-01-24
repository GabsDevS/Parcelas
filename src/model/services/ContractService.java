package model.services;

import java.util.Calendar;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	
	public void setOnlinePaymentService(OnlinePaymentService service) {
		this.onlinePaymentService = service;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(contract.getDate());
		double value = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
			cal.add(Calendar.MONTH, 1);
	
			Double valueInterest = onlinePaymentService.interest(value, i);
			Double valuePaymentFee = onlinePaymentService.paymentFee(valueInterest + value);
			Double valueTotal = value + valuePaymentFee + valueInterest;
			
			contract.getInstallments().add(new Installment(cal.getTime(), valueTotal));
			
			
		}
		
	}

}
