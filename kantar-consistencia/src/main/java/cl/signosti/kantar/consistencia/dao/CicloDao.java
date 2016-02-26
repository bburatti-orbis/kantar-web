package cl.signosti.kantar.consistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.ciclom;



import com.mysql.jdbc.Statement;

public class CicloDao   extends JdbcDaoSupport{
	private static final Logger logger = Logger.getLogger(CicloDao.class);
	
	public int setCiclo(ciclom ciclo) {
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;



		String sql = "INSERT INTO ciclo (fecha_ini,serepite,repetircada,tipo_termino,repeticiones,repetir_hasta,repetirel)values(?,?,?,?,?,?,?)";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, ciclo.getFechaini());
			pre.setInt(2, ciclo.getSe_repite());
			pre.setInt(3, ciclo.getRepetircada());
			pre.setInt(4,ciclo.getTipotermino());
			pre.setInt(5, ciclo.getRepeticiones());
			pre.setString(6,ciclo.getRepetirhasta());
			pre.setInt(7,ciclo.getRepetirel());
			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);

			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" , e);
				}
			}
			if (pre != null) {
				try {
					pre.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" ,
					 e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" ,
					 e);
				}
			}

		}
		return id;

	}
	
	
	
	
public ciclom getciclo(int cod) {
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		ciclom ciclo= new ciclom();
		String sql = "select * from ciclo where id=? ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			rs=pre.executeQuery();
			
			while (rs.next()) {
				ciclo.setId(rs.getInt(1));
				ciclo.setFechaini(rs.getString(2));
				ciclo.setSe_repite(rs.getInt(3));
				ciclo.setRepetircada(rs.getInt(4));
				ciclo.setTipotermino(rs.getInt(5));
				ciclo.setRepeticiones(rs.getInt(6));
				ciclo.setRepetirhasta(rs.getString(7));
				ciclo.setRepetirel(rs.getInt(8));
				

			}
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" ,	 e);
				}
			}
			if (pre != null) {
				try {
					pre.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" ,
					 e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" ,
					 e);
				}
			}

		}
		return ciclo;

	}
}
