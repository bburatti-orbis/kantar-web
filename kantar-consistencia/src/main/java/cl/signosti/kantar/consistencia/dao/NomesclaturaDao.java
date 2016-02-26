package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.mysql.jdbc.Statement;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Nomenclaturam;

public class NomesclaturaDao extends JdbcDaoSupport implements Serializable {
	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger
			.getLogger(NomesclaturaDao.class);

	public int getejecucion(Nomenclaturam nom) throws SQLException {
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "INSERT INTO Nomenclaturas(" + " glosa," + " tipo,"
				+ "estadoCInterna," +"estadoCHistorica," + " Ejecuciones_id) " + "values (?,?,?,?,?)";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, nom.getGlosa());
			pre.setInt(2, nom.getTipo());
			pre.setInt(3, nom.getEstadocinterna());
			pre.setInt(4, 4);
			pre.setInt(5, nom.getId_ejecuciones());
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
		return id;

	}

	public void setErrornomenclatura(int nomen) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "Update Nomenclaturas set estadoCInterna=4"
				+ " where id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, nomen);
			pre.executeUpdate();

			pre.executeUpdate();

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

	}

	public Map<Integer, Nomenclaturam> getnomenclatura(int cod)
			throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Map<Integer, Nomenclaturam> consistencia =new HashMap<Integer, Nomenclaturam>();
		String sql = "select * from nomenclaturas where bases_id=?";
		logger.info("Sql: "+sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);

			rs = pre.executeQuery();
			LocatorDao.getInstance();
			MarcaDao nomen = LocatorDao.getMarcaDao();

			while (rs.next()) {
				Nomenclaturam nom = new Nomenclaturam();

				nom.setId(rs.getInt("id"));
				nom.setGlosa(rs.getString("glosa"));
				nom.setTipo(rs.getInt("tipo"));
				nom.setDetalle(nomen.getmarca(nom.getId()));
				consistencia.put(nom.getId(), nom);

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
		return consistencia;

	}
	
	public void setErrornomenclaturaCH(int nomen,int cod) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "Update Nomenclaturas set estadoCHistorica=?"
				+ " where id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			pre.setInt(2, nomen);
			pre.executeUpdate();

			pre.executeUpdate();

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

	}

}
