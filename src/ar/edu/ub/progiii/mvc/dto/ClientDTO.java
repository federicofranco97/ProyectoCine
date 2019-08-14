package ar.edu.ub.progiii.mvc.dto;

import java.sql.Date;

public class ClientDTO extends PersonDTO{
	private int ClientNumber;

	/**
	 * Constructor
	 * @param fullName representa el nombre completo del cliente.
	 * @param address representa la direccion del cliente.
	 * @param phoneNumber representa el numero de telefono del cliente.
	 * @param email representa el email del cliente.
	 * @param dateOfBirth representa la fecha de nacimiento del cliente.
	 * @param ClientNumber representa el numero de cliente.
	 */
	public ClientDTO(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int ClientNumber) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.ClientNumber = ClientNumber;
	}

	//Getters y Setters
	public int getClientNumber() {
		return ClientNumber;
	}

	private void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

}