package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.Periodom;
import cl.signosti.kantar.consistencia.utils.Close;

public class PeriodosDao extends JdbcDaoSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(PeriodosDao.class);
	
	public Periodom getPeriodo(int idPeriodo){
		Periodom periodo = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		String sql = "SELECT * FROM periodos WHERE id=?";
		
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idPeriodo);
			rs = pre.executeQuery();
			if (rs.next()){
				periodo = new Periodom();
				periodo.setId(rs.getInt("id"));
				periodo.setDirName(rs.getString("dirName"));
				periodo.setMonth(rs.getInt("month"));
				periodo.setPaisId(rs.getInt("paises_id"));
				periodo.setPeriodo(rs.getString("periodo"));
				periodo.setYear(rs.getInt("year"));
			}
		} catch (Exception e) {
			 log.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return periodo;
	}
}
