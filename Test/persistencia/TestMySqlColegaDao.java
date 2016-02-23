package persistencia;

import static org.junit.Assert.*;

import java.util.Date;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.BeforeClass;
import org.junit.Test;



import modelo.Colega;

public class TestMySqlColegaDao {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Bajo el supuesto de que el test de la funcion baja funciona correctamente.
		
		
	}

	@Test
	public void test() {
		
		Colega colega = new Colega(2, "Iker", "San Sebastian", new Date());
	
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc"); // cadena de conexion
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		
		MySqlColegaDao sut =new MySqlColegaDao(ds);
		
		try{
			sut.altaColega(colega);
			Colega colegaObtenido = sut.consultaPorId(colega.getId());
			//Assertos
			assertEquals(colega,colegaObtenido); // 
		}catch (SQLException e){
			e.printStackTrace();
			fail("Se produce un SQLException, y no deberia: "+  e.getMessage());
		}
		
		
	}

}
