package ar.edu.ub.progiii.mvc.dto;

import java.sql.Date;

public class Client extends Person{
	private int ClientNumber;

	public Client(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int ClientNumber) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.ClientNumber=ClientNumber;
	}

	public int getClientNumber() {
		return ClientNumber;
	}

	private void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

}
