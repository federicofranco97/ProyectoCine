package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Data;

class DataTest {
	Connection connection;
	Data dataManager;
	ClientDTO clientDto;
	EmployeeDTO employeeDTO;
	TicketDTO ticketDTO;
	MappingTool mapping;
	
	@BeforeEach
	void setUp() throws Exception {
		connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();
		dataManager = new Data();
		mapping = new MappingTool();
	}
	@Test
	void GetEmployeeByIDtest() throws SQLException {
		String SPsql ="Juan Perez_1161507889_jperez@test.com.ar_Calle Falsa 3399         _1997-11-04 00:00:00.0_2_p3p3_2";
        assertEquals(dataManager.GetEmployeeByID("2"),SPsql);
        assertNotEquals(dataManager.GetEmployeeByID("3"),SPsql);
        assertNotNull(dataManager.GetEmployeeByID("2"));
        assertNull(dataManager.GetEmployeeByID("100"));
	}
	
	@Test
	void GetClientByUIdtest() throws SQLException {
		String SPsql ="Michael Myers_2_116510789_test@email.com_Calle falsa 123_1997-11-08 00:00:00.0";
        assertEquals(dataManager.GetClientByUId("2"),SPsql);
        assertNotEquals(dataManager.GetClientByUId("3"),SPsql);
        assertNotNull(dataManager.GetClientByUId("2"));
        assertNull(dataManager.GetClientByUId("100"));
	}
	
	@Test
	void GetAllFilmstest() throws SQLException {
		String SPsql ="1_Bambi_180_/2_Terminator 8_138_/";
		String SPsql2 = "2_bambi_180_/2_Terminator 8_138";
        assertEquals(dataManager.GetAllFilms(),SPsql);
        assertNotEquals(dataManager.GetClientByUId("3"),SPsql2);
        assertNotNull(dataManager.GetAllFilms());
	}
	
	@Test
	void GetBookingByIdtest() throws SQLException {
		String SPsql ="2_1_1_2019-08-13 19:49:18.81_1_3_2_2_1_1/";
        assertEquals(dataManager.GetBookingById("2"),SPsql);
        assertNotEquals(dataManager.GetBookingById("3"),SPsql);
        assertNotNull(dataManager.GetBookingById("2"));
        assertNull(dataManager.GetBookingById("14"));
	}
	
	@Test
	void PostNewClienttest() throws SQLException {
		clientDto = new ClientDTO("Diego Moran","11 de septiembre", "1530042275","diegomoran_777@hotmail.com","1997-11-08 00:00:00.0");
		dataManager.PostNewClient(clientDto);
		String result = "";
		Statement stm = connection.createStatement();
        String query="select Email from cliente where Email='diegomoran_777@hotmail.com' and NombreCompleto='Diego Moran'";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("Email"));
        }
        assertEquals(result,"diegomoran_777@hotmail.com");
        assertNotEquals(result,"");
        assertNotEquals(result,null);
	}
	
	@Test
	void GetAllClientstest() throws SQLException {
		String SPsql ="Michael Myers_2_116510789_test@email.com_Calle falsa 123_1997-11-08 00:00:00.0/Marcos Esteban_3_5559301_marcos@test.com_Calle falsa 223_1987-03-20 00:00:00.0/Jose Listorti_5_15617848_Listorti@test.com.ar_Calle falsa 55432_1965-08-14 00:00:00.0/Diego Moran_10003_1530042275_diegomoran_777@hotmail.com_11 de septiembre_1997-11-08 00:00:00.0/";
		String SPsql2 = "2_bambi_180_/2_Terminator 8_138";
        assertEquals(dataManager.GetAllClients(),SPsql);
        assertNotEquals(dataManager.GetAllClients(),SPsql2);
        assertNotNull(dataManager.GetAllClients());
	}
	
	@Test
	void CheckEmployeeCategorytest() throws SQLException {
        assertEquals(dataManager.CheckEmployeeCategory(2),"2");
        assertNotEquals(dataManager.CheckEmployeeCategory(2),"3");
        assertNotNull(dataManager.CheckEmployeeCategory(2));
        assertNull(dataManager.CheckEmployeeCategory(100));
	}
	
	@Test
	void GetAllEmployeestest() throws SQLException {
		String SPsql = "Juan Perez_1161507889_jperez@test.com.ar_Calle Falsa 3399_1997-11-04 00:00:00.0_2_2/Fabian Perez_578988699_fabianp@prueba.com_Calle falsa 223_1997-11-15 00:00:00.0_3_2/Federico Franco_1161507633_federicoa.franco@comunidad.ub.edu.ar_Pedro Goyena 3500_1997-11-04 00:00:00.0_4_1/Belen De Vicente_1161507634_mariab.de@comunidad.ub.edu.ar_Pedro Goyena 3510_1995-01-01 00:00:00.0_5_1/Diego Moran_1161507635_diego.moran@comunidad.ub.edu.ar_Pedro Goyena 3512_1995-01-02 00:00:00.0_6_4/";
		String SPsql2 = "2_bambi_180_/2_Terminator 8_138";
		String result="";
		Statement stm = connection.createStatement();
        String query="select * from empleado where codrol<>5";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("NombreCompleto").trim())+"_";
            result += (rst.getString("Telefono").trim())+"_";
            result += (rst.getString("Email").trim())+"_";
            result += (rst.getString("Direccion").trim())+"_";
            result += (rst.getString("FechaNac").trim())+"_";
            result += (rst.getString("NroEmpleado").trim())+"_";
            result += (rst.getString("CodRol").trim()+"/");
        }
		String [] aux = result.split("_");
		int cont = aux.length;
		String [] aux2 = dataManager.GetAllEmployees().split("_");
		int cont2 = aux2.length;
        assertEquals(dataManager.GetAllEmployees(),SPsql);
        assertNotEquals(dataManager.GetAllEmployees(),SPsql2);
        assertNotNull(dataManager.GetAllEmployees());
        assertEquals(cont,cont2);
        assertEquals(dataManager.GetAllEmployees(),result);
	}
	
	@Test
	void BanEmployeetest() throws SQLException {
        assertEquals(dataManager.BanEmployee(6),1);
        assertNotEquals(dataManager.BanEmployee(6),0);
        assertNotNull(dataManager.BanEmployee(6));
	}
	
	@Test
	void DeleteEmployeetest() throws SQLException {
        assertEquals(dataManager.DeleteEmployee(7),1);
        assertNotEquals(dataManager.DeleteEmployee(7),0);
        assertNotNull(dataManager.DeleteEmployee(7));
        assertEquals(dataManager.DeleteEmployee(100),0);
	}
	
	@Test
	void UpdateProfiletest() throws SQLException {
		employeeDTO = new EmployeeDTO("rick wakem","uruguay","1530042277","ricks@test.com","2001022",8,"5");
		dataManager.UpdateProfile(employeeDTO);
		String result = "";
		Statement stm = connection.createStatement();
        String query="select Email from empleado where Email='rick@test.com' and NombreCompleto='rick wakem'";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("Email"));
        }
        
        assertEquals(dataManager.UpdateProfile(employeeDTO),1);
        
        String result2 = "";
		Statement stm2 = connection.createStatement();
        String query2="select Email from empleado where Email='ricks@test.com' and NombreCompleto='rick wakem'";
        ResultSet rst2 = stm2.executeQuery(query2);
        while(rst2.next()) {
            result2 += (rst2.getString("Email"));
        }
        
        assertNotEquals(result,result2);
        assertNotEquals(result,0);
	}
	
	@Test
	void GetAllTicketstest() throws SQLException {
		String SPsql = "1_#0547_Juan test_Pedido de desbaneo de Juan_2019-08-20 19:41:27.08_Cerrado/2_#0548_Juan test_Pedido de desbaneo de Marcos_2019-08-20 19:41:33.233_Cerrado/3_#0549_Juan test_Pedido de desbaneo de Damian_2019-08-20 19:41:37.073_Activo/4_#0550_Juan test_Pedido de desbaneo de Marcelo_2019-08-20 20:30:53.91_Activo/5_#0551_Federico Franco_Remover borrado de lucas_2019-08-20 20:42:11.08_Activo/6_Test Front_Marcos Peña_Ticket creado desde el front_2019-08-24 12:45:53.21_Cerado/7_Creacion Ticket_Federico Franco_Prueba de creacion de ticket_2019-08-24 17:39:01.013_Cerado/8_Prueba Tutorial_Federico Franco_esto es una prueba para agregar un ticket_2019-08-24 20:12:42.257_Cerado/9_Ticket De prueba_usuario prueba_Mensaje de prueba_2019-08-24 20:16:09.32_Cerado/10_Prueba Tutorial_Federico Franco_esto es una prueba para agregar un ticket_2019-08-24 20:27:09.69_Activo/";
		String SPsql2 = "2_bambi_180_/2_Terminator 8_138";
		String result = "";
		Statement stm = connection.createStatement();
        String query="select * from tickets";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("ID").trim())+"_";
            result += (rst.getString("Title").trim())+"_";
            result += (rst.getString("Employee").trim())+"_";
            result += (rst.getString("Mensaje").trim())+"_";
            result += (rst.getString("Fecha").trim()+"_");
            result += (rst.getString("Estado").trim()+"/");
        }
		String [] aux = result.split("_");
		int cont = aux.length;
		String [] aux2 = dataManager.GetAllTickets().split("_");
		int cont2 = aux2.length;
        assertEquals(dataManager.GetAllTickets(),SPsql);
        assertNotEquals(dataManager.GetAllTickets(),SPsql2);
        assertNotNull(dataManager.GetAllTickets());
        assertEquals(cont,cont2);
        assertEquals(dataManager.GetAllTickets(),result);
	}
	
	@Test
	void GetActiveTicketstest() throws SQLException {
		String SPsql = "3_#0549_Juan test_Pedido de desbaneo de Damian_2019-08-20 19:41:37.073_Activo/4_#0550_Juan test_Pedido de desbaneo de Marcelo_2019-08-20 20:30:53.91_Activo/5_#0551_Federico Franco_Remover borrado de lucas_2019-08-20 20:42:11.08_Activo/10_Prueba Tutorial_Federico Franco_esto es una prueba para agregar un ticket_2019-08-24 20:27:09.69_Activo/11_#t123_diego moran_ticket test_2019-08-25 13:04:37.22_Activo/";
		String SPsql2 = "2_bambi_180_/2_Terminator 8_138";
		String result = "";
		Statement stm = connection.createStatement();
        String query="select * from tickets where estado='activo'";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("ID").trim())+"_";
            result += (rst.getString("Title").trim())+"_";
            result += (rst.getString("Employee").trim())+"_";
            result += (rst.getString("Mensaje").trim())+"_";
            result += (rst.getString("Fecha").trim()+"_");
            result += (rst.getString("Estado").trim()+"/");
        }
		String [] aux = result.split("_");
		int cont = aux.length;
		String [] aux2 = dataManager.GetActiveTickets().split("_");
		int cont2 = aux2.length;
        assertEquals(dataManager.GetActiveTickets(),SPsql);
        assertNotEquals(dataManager.GetActiveTickets(),SPsql2);
        assertNotNull(dataManager.GetActiveTickets());
        assertEquals(cont,cont2);
        assertEquals(dataManager.GetActiveTickets(),result);
	}
	
	@Test
	void AddTickettest() throws SQLException {
		ticketDTO  = new TicketDTO("#t123","diego moran","ticket test","132","20191022","ok");
		//dataManager.AddTicket(ticketDTO);
		String result = "";
		Statement stm = connection.createStatement();
        String query="select id from tickets where id=11";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("id"));
        }
        assertEquals(result,"11");
        assertNotEquals(result,"");
        assertNotEquals(result,null);
	}
}
