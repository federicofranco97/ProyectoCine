package ar.edu.ub.progiii.mvc.dto;

import java.sql.Date;

public class EmployeeDTO extends PersonDTO{
	private int EmployeeNumber;
	private String Rank;
	
	/**
	 * Constructor
	 * @param fullName representa el nombre del empleado.
	 * @param address representa la direccion del empleado.
	 * @param phoneNumber representa el numero de telefono del empleado.
	 * @param email representa el email del empleado.
	 * @param dateOfBirth representa la fecha de nacimiento del empleado.
	 * @param EmployeeNumber representa el numero de empleado.
	 * @param Rank representa la categoria del empleado.
	 */
	public EmployeeDTO(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int EmployeeNumber,String Rank) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.EmployeeNumber = EmployeeNumber;
		this.Rank = Rank;
	}
	
	//Getters y Setters
	public int getEmployeeNumber() {
		return EmployeeNumber;
	}

	private void setEmployeeNumber(int employeeNumber) {
		EmployeeNumber = employeeNumber;
	}

	public String getRank() {
		return Rank;
	}

	private void setRank(String rank) {
		Rank = rank;
	}
		
	

}
