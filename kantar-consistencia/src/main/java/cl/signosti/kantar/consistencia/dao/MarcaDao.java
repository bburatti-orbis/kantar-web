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

import cl.signosti.kantar.consistencia.modelo.Marcam;
import cl.signosti.kantar.consistencia.utils.Nivel;

import com.mysql.jdbc.Statement;

public class MarcaDao extends JdbcDaoSupport implements Serializable {
	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger.getLogger(MarcaDao.class);

	public int setMarca(Marcam marca) {
		String sql = "Insert into Marcas (glosa,Nomenclatura_id,linea,valor,estadoCInterna,nivel) values (?,?,?,?,?,?)";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int devuelta = 0;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, marca.getGlosa());
			pre.setInt(2, marca.getNomenclatura());
			pre.setInt(3, marca.getLinea());
			pre.setInt(4, marca.getValor());
			pre.setInt(5, marca.getEstadoCinterna());
			pre.setInt(6, marca.getNivel());
			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				devuelta = rs.getInt(1);

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
		return devuelta;

	}

	public void setEstadoError(Nivel nivel) {
		String sql = "Update Marcas set estadoCInterna=4," + "sumaHijos=?"
				+ " where id=?";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, nivel.getSumaHijos());
			pre.setInt(2, nivel.getId());

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

	public Map<Integer, Marcam> getmarca(int cod) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Map<Integer, Marcam> consistencia = new HashMap<Integer, Marcam>();
		String sql = "select * from marcas where nomenclatura_id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);

			rs = pre.executeQuery();

			while (rs.next()) {
				Marcam nom = new Marcam();

				nom.setId(rs.getInt("id"));
				nom.setGlosa(rs.getString("glosa"));
				nom.setLinea(rs.getInt("linea"));
				nom.setValor(rs.getInt("valor"));
				nom.setNivel(rs.getInt("nivel"));
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

}
