package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

class ClientServiceTest {

	Connection connection;
	Data dataManager;
	ClientDTO clientDto;
	EmployeeDTO employeeDTO;
	TicketDTO ticketDTO;
	MappingTool mapping;
	ClientService clientService;
	BookingDTO bookingDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		dataManager = new Data();
		mapping = new MappingTool();
		clientService = new ClientService();
	}
	
	@Test
	void IsEmployeeAlowedtest() {
		assertTrue(clientService.IsEmployeeAlowed(2));
		assertFalse(clientService.IsEmployeeAlowed(6));
		assertFalse(clientService.IsEmployeeAlowed(7));
	}
	
	@Test
	void verifyEmployeeLogintest() {
		assertTrue(clientService.verifyEmployeeLogin("2","p3p3"));
		assertFalse(clientService.verifyEmployeeLogin("2","p4p4"));
		assertFalse(clientService.verifyEmployeeLogin("6","d13g0"));
		assertFalse(clientService.verifyEmployeeLogin("7","g9s7a9o"));
	}
	
	@Test
	void GetClientByUIDtest() {
	    clientDto = new ClientDTO("Diego Moran","11 de septiembre", "1530042275","diegomoran_777@hotmail.com","1997-11-08 00:00:00.0");
		assertNotNull(clientService.GetClientByUID("3"));
		assertNull(clientService.GetClientByUID("100"));
		assertTrue(clientService.GetClientByUID("2") instanceof ClientDTO);
		assertEquals(clientService.GetClientByUID("10003").getFullName(),clientDto.getFullName());
	}
	
	@Test
	void GetAllFilmstest() {
		String nombres = "";
		assertEquals(clientService.GetAllFilms().size(),2);
		assertNotEquals(clientService.GetAllFilms().size(),3);
		for (FilmDTO film : clientService.GetAllFilms()) {
			nombres += film.getFilmName();
		}
		assertEquals(nombres,"BambiTerminator 8");
		assertNotNull(clientService.GetAllFilms());
	}
	
	@Test
	void GetBookingByIdtest() {
	     bookingDTO = new BookingDTO("2", "2019-08-13", "1", "1", "1",1, 3, 2, 2, 1,350.55);
		assertNotNull(clientService.GetBookingById("2"));
		assertNull(clientService.GetBookingById("100"));
		assertTrue(clientService.GetBookingById("2") instanceof BookingDTO);
		assertEquals(clientService.GetBookingById("2").getBookingCode(),bookingDTO.getBookingCode());
	}
	
	/*@Test
	void CreateNewClienttest() {
		clientDto = new ClientDTO("Samanta Wallace","11 de septiembre", "1564760343","sam@hotmail.com","1997-11-08");
		assertTrue(clientService.CreateNewClient(clientDto));
		assertNotNull(clientService.CreateNewClient(clientDto));
	}*/
	
	@Test
	void GetAllClientstest() {
		String numbers = "";
		assertEquals(clientService.GetAllClients().size(),5);
		assertNotEquals(clientService.GetAllClients().size(),3);
		for (ClientDTO client : clientService.GetAllClients()) {
			numbers += client.getClientNumber();
		}
		assertEquals(numbers,"2351000310005");
		assertNotNull(clientService.GetAllClients());
	}
	
	/*@Test
	void GetEmployeeCategorytest() {
		assertEquals(clientService.GetEmployeeCategory(2),2);
		assertNull(clientService.GetEmployeeCategory(100));
		assertNotNull(3);
	}*/
	
	@Test
	void GetAllEmployeestest() {
		String numbers = "";
		assertEquals(clientService.GetAllEmployees().size(),5);
		assertNotEquals(clientService.GetAllEmployees().size(),3);
		for (EmployeeDTO employee : clientService.GetAllEmployees()) {
			numbers += employee.getEmployeeNumber();
		}
		assertEquals(numbers,"23456");
		assertNotNull(clientService.GetAllClients());
		assertTrue(clientService.GetAllClients() instanceof ArrayList);
	}
	
	@Test
	void BanEmployeetest() {
		assertEquals(clientService.BanEmployee(6),1);
		assertEquals(clientService.BanEmployee(100),0);
	}
	
	@Test
	void DeleteEmployeetest() {
		assertEquals(clientService.DeleteEmployee(5),1);
		assertEquals(clientService.DeleteEmployee(100),0);
	}
	
	@Test
	void GetEmployeetest() {
		assertEquals(clientService.GetEmployee(6).getEmployeeNumber(),6);
		assertNull(clientService.GetEmployee(100));
		assertTrue(clientService.GetEmployee(6) instanceof EmployeeDTO);
	}
	
	@Test
	void UpdateEmployeetest() {
		employeeDTO = new EmployeeDTO();
		employeeDTO.setAddress("uruguay");
		employeeDTO.setEmail("rick@prueba.com");
		employeeDTO.setPhoneNumber("1530042888");
		employeeDTO.setFullName("rick wakem");
		employeeDTO.setDateOfBirth("2000-10-22");
		clientService.UpdateEmployee(employeeDTO);
		assertEquals(clientService.GetEmployee(8).getAddress(),"uruguay");
		assertEquals(clientService.GetEmployee(8).getEmail(),"rick@prueba.com");
		assertEquals(clientService.GetEmployee(8).getPhoneNumber(),"1530042888");
	}
	
	@Test
	void GetAllTicketstest() {
		String numbers = "";
		assertEquals(clientService.GetAllTickets().size(),11);
		assertNotEquals(clientService.GetAllTickets().size(),3);
		for (TicketDTO ticket : clientService.GetAllTickets()) {
			numbers += ticket.getTicketID();
		}
		assertEquals(numbers,"1234567891011");
		assertNotNull(clientService.GetAllTickets());
		assertTrue(clientService.GetAllTickets() instanceof ArrayList);
	}
	
	@Test
	void GetActiveTicketstest() {
		String numbers = "";
		int count = 0;
		assertEquals(clientService.GetActiveTickets().size(),5);
		assertNotEquals(clientService.GetActiveTickets().size(),3);
		for (TicketDTO ticketA : clientService.GetActiveTickets()) {
			if(!ticketA.getTicketStatus().equalsIgnoreCase("activo")) {
				count += 1;
			}
			numbers += ticketA.getTicketID();
		}
		assertEquals(numbers,"3451011");
		assertNotNull(clientService.GetActiveTickets());
		assertTrue(clientService.GetActiveTickets() instanceof ArrayList);
		assertEquals(count, 0);
	}
	
	@Test
	void IsActiveUsertest() {
		clientService.currentEmployee  = new EmployeeDTO();
		assertFalse(clientService.IsActiveUser());
		clientService.currentEmployee  = new  EmployeeDTO("rick wakem","uruguay","1530042277","ricks@test.com","2001022",8,"5");
		assertTrue(clientService.IsActiveUser());
	}
	
	
	
}
