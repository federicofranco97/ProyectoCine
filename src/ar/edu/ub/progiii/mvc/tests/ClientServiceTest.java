package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

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
}
