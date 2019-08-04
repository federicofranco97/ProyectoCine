package ar.edu.ub.progiii.mvc.dto;

import java.sql.Date;

public class EmployeeDTO extends PersonDTO{
	private int EmployeeNumber;
	private String Rank;
	
	public EmployeeDTO(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int EmployeeNumber,String Rank) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.EmployeeNumber=EmployeeNumber;
		this.Rank=Rank;
	}

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
