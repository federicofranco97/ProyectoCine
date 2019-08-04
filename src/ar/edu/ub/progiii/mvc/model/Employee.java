package ar.edu.ub.progiii.mvc.model;

import java.sql.Date;

public class Employee extends Person{
	private int EmployeeNumber;

	public Employee(String fullName, String address, String phoneNumber, String email, Date dateOfBirth,int EmployeeNumber) {
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
