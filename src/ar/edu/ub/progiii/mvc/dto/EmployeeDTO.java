package ar.edu.ub.progiii.mvc.dto;

import java.sql.Date;

public class EmployeeDTO extends PersonDTO{
	private int EmployeeNumber;

	public EmployeeDTO(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int EmployeeNumber) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.EmployeeNumber=EmployeeNumber;
	}

	public int getEmployeeNumber() {
		return EmployeeNumber;
	}

	private void setEmployeeNumber(int employeeNumber) {
		EmployeeNumber = employeeNumber;
	}
		

}
