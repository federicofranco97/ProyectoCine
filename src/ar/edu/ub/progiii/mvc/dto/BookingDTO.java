package ar.edu.ub.progiii.mvc.dto;

import java.util.Date;

public class BookingDTO {
	private String BookingCode;
	private Date BookingDate;
	private String MovieName;
	private String Show;
	private String Location;
	private int TheatreNumber;
	private int TicketAmount;
	private int ClientNumber;
	private int BookingStatus;
	private int ChannelCode;
	private double TotalValue;
	
	public BookingDTO(String bookingCode, Date bookingDate, String movieName, String show, String location,
			int theatreNumber, int ticketAmount, int clientNumber, int bookingStatus, int channelCode,double totalValue) {
		
		BookingCode = bookingCode;
		BookingDate = bookingDate;
		MovieName = movieName;
		Show = show;
		Location = location;
		TheatreNumber = theatreNumber;
		TicketAmount = ticketAmount;
		ClientNumber = clientNumber;
		BookingStatus = bookingStatus;
		ChannelCode = channelCode;
		TotalValue = totalValue;
	}

	public double getTotalValue() {
		return TotalValue;
	}

	public String getBookingCode() {
		return BookingCode;
	}

	public Date getBookingDate() {
		return BookingDate;
	}

	public String getMovieName() {
		return MovieName;
	}

	public String getShow() {
		return Show;
	}

	public String getLocation() {
		return Location;
	}

	public int getTheatreNumber() {
		return TheatreNumber;
	}

	public int getTicketAmount() {
		return TicketAmount;
	}

	public int getClientNumber() {
		return ClientNumber;
	}

	public int getBookingStatus() {
		return BookingStatus;
	}

	public int getChannelCode() {
		return ChannelCode;
	}

	private void setBookingCode(String bookingCode) {
		BookingCode = bookingCode;
	}

	private void setBookingDate(Date bookingDate) {
		BookingDate = bookingDate;
	}

	private void setMovieName(String movieName) {
		MovieName = movieName;
	}

	private void setShow(String show) {
		Show = show;
	}

	private void setLocation(String location) {
		Location = location;
	}

	private void setTheatreNumber(int theatreNumber) {
		TheatreNumber = theatreNumber;
	}

	private void setTicketAmount(int ticketAmount) {
		TicketAmount = ticketAmount;
	}

	private void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

	private void setBookingStatus(int bookingStatus) {
		BookingStatus = bookingStatus;
	}

	private void setChannelCode(int channelCode) {
		ChannelCode = channelCode;
	}
	
	private void setTotalValue(double totalValue) {
		TotalValue = totalValue;
	}

	

}
