package ar.edu.ub.progiii.mvc.dto;

import java.sql.Date;

public class ClientDTO extends PersonDTO{
	private int ClientNumber;
	private String ClientStatus;

	/**
	 * Constructor
	 * @param fullName representa el nombre completo del cliente.
	 * @param address representa la direccion del cliente.
	 * @param phoneNumber representa el numero de telefono del cliente.
	 * @param email representa el email del cliente.
	 * @param dateOfBirth representa la fecha de nacimiento del cliente.
	 * @param ClientNumber representa el numero de cliente.
	 */
	public ClientDTO(String fullName, String address, String phoneNumber, String email, String dateOfBirth,int ClientNumber) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.ClientNumber = ClientNumber;
	}

	public ClientDTO(String fullName, String address, String phoneNumber, String email, String dateOfBirth) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
	}

	public ClientDTO(){}

	//Getters y Setters
	public int getClientNumber() {
		return ClientNumber;
	}

	public void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

	public String getClientStatus() {
		return ClientStatus;
	}

	public void setClientStatus(String clientStatus) {
		ClientStatus = clientStatus;
	}
}
