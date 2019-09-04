package ar.edu.ub.progiii.mvc.model;

import java.sql.Date;

public class Employee extends Person{
	private int EmployeeNumber;
	private String HashedPassword;
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
	public Employee(String fullName, String address, String phoneNumber, String email, String dateOfBirth,int EmployeeNumber,
			String HashedPassword,String Rank) {
		
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.EmployeeNumber = EmployeeNumber;
		this.HashedPassword = HashedPassword;
		this.Rank = Rank;
	}

	public Employee(){}

	//Getters y Setters
	public int getEmployeeNumber() {
		return EmployeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		EmployeeNumber = employeeNumber;
	}

	public String getHashedPassword() {
		return HashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		HashedPassword = hashedPassword;
	}

	public String getRank() {
		return Rank;
	}

	public void setRank(String rank) {
		Rank = rank;
	}
	
}
