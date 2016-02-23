package dao;



import java.sql.SQLException;
import java.util.Collection;

import modelo.Colega;

public interface ColegaDao {
	
	void altaColega(Colega colega) throws SQLException;
	void bajaColega(int id) throws SQLException;
	void modificarColega(Colega colega) throws SQLException;
	
	Colega consultaPorId ( int id) throws SQLException;
	Collection<Colega>consultarTodos () throws SQLException;

}
