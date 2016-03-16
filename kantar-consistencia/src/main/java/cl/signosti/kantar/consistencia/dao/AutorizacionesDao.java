package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AutorizacionesDao extends JdbcDaoSupport implements Serializable {

	private static final long serialVersionUID = 1L;
//	private static final Logger logger = Logger.getLogger(AutorizacionesDao.class);
	
	public void consistencia(int idEjecucion, String glosa, int proceso, int idUsuario) throws SQLException{
		Connection conn = null;
		PreparedStatement pre = null;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO autorizadas(")
			.append("Ejecuciones_id, proceso, glosa, Usuarios_id, created_at")
			.append(") VALUES (")
			.append("?, ?, ?, ?, NOW()")
			.append(")");
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, idEjecucion);
			pre.setInt(2, proceso);
			pre.setString(3, glosa);
			pre.setInt(4, idUsuario);
		
			pre.executeUpdate();
		} finally {
			if(pre != null){
				pre.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
	
	
}
