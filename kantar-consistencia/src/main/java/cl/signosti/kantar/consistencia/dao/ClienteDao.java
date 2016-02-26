package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Clientem;

public class ClienteDao extends JdbcDaoSupport implements Serializable {
	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger.getLogger(ClienteDao.class);

	public Clientem getCliente() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Clientem entidad = new Clientem();

		String sql = "SELECT * FROM clientes";
		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			BasesDao bases = LocatorDao.getBasesDao();

			while (rs.next()) {
				entidad.setId(rs.getInt("id"));
				entidad.setNombre(rs.getNString("nombre"));
				entidad.setBases(bases.getCliente(entidad.getId()));

			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					 logger.error("Error, causa:" ,
					 e);
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

		return entidad;
	}
}
