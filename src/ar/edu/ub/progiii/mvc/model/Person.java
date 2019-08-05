package ar.edu.ub.progiii.mvc.model;

import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Person {

	private String FullName;
	private String Address;
	private String PhoneNumber;
	private String Email;
	private Date DateOfBirth;
	
	public Person() {}

	public Person(String fullName, String address, String phoneNumber, String email, Date dateOfBirth) {
	
		FullName = fullName;
		Address = address;
		PhoneNumber = phoneNumber;
		Email = email;
		DateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return FullName;
	}

	private void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getAddress() {
		return Address;
	}

	private void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	private void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	private void setEmail(String email) {
		Email = email;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	private void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	
}
