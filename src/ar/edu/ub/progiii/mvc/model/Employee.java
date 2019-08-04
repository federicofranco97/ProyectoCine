package ar.edu.ub.progiii.mvc.model;

import java.sql.Date;

public class Employee extends Person{
	private int EmployeeNumber;
	private String HashedPassword;
	private String Rank;

	public Employee(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int EmployeeNumber,
			String HashedPassword,String Rank) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.EmployeeNumber=EmployeeNumber;
		this.HashedPassword=HashedPassword;
		this.Rank=Rank;
	}

	public int getEmployeeNumber() {
		return EmployeeNumber;
	}

	private void setEmployeeNumber(int employeeNumber) {
		EmployeeNumber = employeeNumber;
	}

	public String getHashedPassword() {
		return HashedPassword;
	}

	private void setHashedPassword(String hashedPassword) {
		HashedPassword = hashedPassword;
	}

	public String getRank() {
		return Rank;
	}

	private void setRank(String rank) {
		Rank = rank;
	}
	
	
}
