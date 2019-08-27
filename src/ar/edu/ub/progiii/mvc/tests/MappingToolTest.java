package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

class MappingToolTest {

	java.sql.Connection connection;
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
		connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();
	}
	
	@Test
	void MapDTOEmployeetest() {
		employee = new Employee("diego Moran", "11 de septiembre", "153004777", "diego@hotmail.com", "2000-10-2", 5, "ert5", "2");
		assertEquals(mapping.MapDTOEmployee(employee).getFullName(),employee.getFullName());
		assertTrue(mapping.MapDTOEmployee(employee) instanceof EmployeeDTO);
	}
	
	@Test
	void MapSQLBookingDTOtest() {
		String SPsql ="2_1_1_2019-08-13 19:49:18.81_1_3_2_2_1_1/";
		assertEquals(mapping.MapSQLBookingDTO(SPsql).getTotalValue(),1);
		assertTrue(mapping.MapSQLBookingDTO(SPsql) instanceof BookingDTO);
	}
	
	@Test
	void MapDTOClientSQLtest() {
		String SQLData = dataManager.GetClientByUId("2");
		assertEquals(mapping.MapDTOClientSQL(SQLData).getFullName(),"Michael Myers");
		assertTrue(mapping.MapDTOClientSQL(SQLData) instanceof ClientDTO);
	}
	
	@Test
	void MapDTOFilmSQLtest() throws SQLException {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            String query="select * from pelicula where id=2";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                /*result += (rst.getString("NombreCompleto"))+"_";
                result += (rst.getString("Telefono"))+"_";
                result += (rst.getString("Email"))+"_";
                result += (rst.getString("Direccion"))+"_";
                result += (rst.getString("FechaNac"))+"_";
                result += (Integer.parseInt(rst.getString("id")))+"_";
                result += (rst.getString("Clave"))+"_";
                result += (rst.getString("CodRol"));*/
            }
       }
		assertEquals(mapping.MapDTOFilmSQL(result).getCode(),2);
		assertTrue(mapping.MapDTOFilmSQL(result) instanceof FilmDTO);
	}

	@Test
	void MapDTOEmployeeSQLtest() {
		String SQLData = dataManager.GetEmployeeByID("2");
		assertEquals(mapping.MapDTOEmployeeSQL(SQLData).getEmployeeNumber(),2);
		assertTrue(mapping.MapDTOEmployeeSQL(SQLData) instanceof EmployeeDTO);
	}
	
	@Test
	void MapDTOTicketSQLtest() {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            //String query="select * from ticket where id=2";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                /*result += (rst.getString("NombreCompleto"))+"_";
                result += (rst.getString("Telefono"))+"_";
                result += (rst.getString("Email"))+"_";
                result += (rst.getString("Direccion"))+"_";
                result += (rst.getString("FechaNac"))+"_";
                result += (Integer.parseInt(rst.getString("id")))+"_";
                result += (rst.getString("Clave"))+"_";
                result += (rst.getString("CodRol"));*/
            }
       }
		assertEquals(mapping.MapDTOTicketSQL(result).getTicketID(),1);
		assertTrue(mapping.MapDTOTicketSQL(result) instanceof TicketDTO);
	}
	
	@Test
	void MapEmployeeSQLtest() {
		String SQLData = dataManager.GetEmployeeByID("2");
		assertEquals(mapping.MapEmployeeSQL(SQLData).getEmployeeNumber(),2);
		assertTrue(mapping.MapEmployeeSQL(SQLData) instanceof Employee);
	}
}
