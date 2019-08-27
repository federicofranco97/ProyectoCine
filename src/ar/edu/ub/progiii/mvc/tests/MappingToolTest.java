package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

class MappingToolTest {

	Connection connection;
	Data dataManager;
	ClientDTO clientDto;
	EmployeeDTO employeeDTO;
	TicketDTO ticketDTO;
	MappingTool mapping;
	ClientService clientService;
	BookingDTO bookingDTO;
	Employee employee;
	
	
	@BeforeEach
	void setUp() throws Exception {
		dataManager = new Data();
		mapping = new MappingTool();
		clientService = new ClientService();
	}
	
	@Test
	void MapDTOEmployeetest() {
		employee = new Employee("diego Moran", "11 de septiembre", "153004777", "diego@hotmail.com", "2000-10-2", 5, "ert5", "2");
		assertEquals(mapping.MapDTOEmployee(employee).getFullName(),employee.getFullName());
		assertTrue(mapping.MapDTOEmployee(employee) instanceof EmployeeDTO);
	}

}
