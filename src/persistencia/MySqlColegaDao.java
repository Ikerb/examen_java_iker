package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import javax.sql.DataSource;


import dao.ColegaDao;
import modelo.Colega;


public class MySqlColegaDao implements ColegaDao{
	
	private static final String INSERT_COLEGA = "INSERT INTO colega (id,nombre,ciudad,fecha) VALUES (?,?,?,?)"; // final pq es una constante que no varía
									// static pq es unico para esa tipología, no
									// varía

	private static final String DELETE_COLEGA = "DELETE FROM colega WHERE id = ?"; // Borrar registro/dar de baja

	private static final String UPDATE_COLEGA = "UPDATE colega SET id = ?, nombre = ?, ciudad = ? WHERE fecha = ?"; // modificar un registro de colega

	private static final String SELECT_COLEGA_ID = "SELECT * FROM colega WHERE id = ?"; // consulta por ID de colegas

	private static final String SELECT_COLEGA = "SELECT * FROM colega"; // consulta todos los registros


	private DataSource ds;

	public MySqlColegaDao(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	@Override
	public void altaColega(Colega colega) throws SQLException {
		
		Connection connection = null;

		try {
			// 1- Obtener la conexion
			connection = ds.getConnection();
			// 2- Obtener STATEMENT Construir sentancias con staments, de forrma
			// directa o preprocesado
			// Son las sentencias del SQL, definida en formato string
			// Habrá que envolverlo con un statement
			PreparedStatement ps = connection.prepareStatement(INSERT_COLEGA);
			ps.setInt(1, colega.getId());
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, new java.sql.Date( colega.getFecha().getTime()));
			// 3- Ejecutar la sentencia.
			// execute query/update
			ps.executeUpdate();
			// 4- (Opcional con sentencia QUERY)Procesado de los resultados

		} finally {
			// 5- Cerrar la conexion
			if (connection != null) {
				connection.close();
			}
		}
		
	}

	@Override
	public void bajaColega(int id) throws SQLException {
		Connection connection = null;
		try {
			
			connection = ds.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(DELETE_COLEGA);
			ps.setInt(1, id); 

			ps.executeUpdate();
			

		} finally {
			
			if (connection != null) {
				connection.close();
			}
		}
		
	}

	@Override
	public void modificarColega(Colega colega) throws SQLException {
		
		Connection connection = null;

		try {
			// 1- Obtener la conexion
			connection = ds.getConnection();
			// 2- Obtener STATEMENT Construir sentancias con staments, de forrma
			// directa o preprocesado
			// Son las sentencias del SQL, definida en formato string
			// Habrá que envolverlo con un statement
			PreparedStatement ps = connection.prepareStatement(UPDATE_COLEGA);
			ps.setInt(1, colega.getId());
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, new java.sql.Date( colega.getFecha().getTime()));
			// 3- Ejecutar la sentencia.
			// execute query/update
			ps.executeUpdate();
			// 4- (Opcional con sentencia QUERY)Procesado de los resultados

		} finally {
			// 5- Cerrar la conexion
			if (connection != null) {
				connection.close();
			}
		}
		
	}

	@Override
	public Colega consultaPorId(int id) throws SQLException {
		
		Connection connection = null;

		try {
			
			connection = ds.getConnection();
			//preparar conexion
			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA_ID);
			ps.setInt(1, id);

			//ejecutar statement
			ResultSet rs = ps.executeQuery();
			

			if (rs.first()) {
				
				return new Colega(id, rs.getString("nombre"), rs.getString("ciudad"), rs.getDate("fecha"));

			}
			return null;
		} finally {
			// 5- Cerrar la conexion
			if (connection != null) {
				connection.close();
			}
		}
		
		
		
	}

	@Override
	public Collection<Colega> consultarTodos() throws SQLException {
		
		Connection connection = null;
		Collection<Colega> resultado = new HashSet<>();

		try {
			
			connection = ds.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA);

			ResultSet rs = ps.executeQuery();
			
			if (rs.first()) {
				
				do {
				
					Colega colega = new Colega(rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"),
							rs.getDate("fecha"));
					resultado.add(colega);
				} while (rs.next());
			}
			return resultado;
		} finally {
			
			if (connection != null) {
				connection.close();
			}
		}
	}
}
