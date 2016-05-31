package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Paisesm;
import cl.signosti.kantar.consistencia.utils.Close;

import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class PaisesDao extends JdbcDaoSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PaisesDao.class);

	public Map<Integer, Paisesm> getlistapaises() {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Map<Integer, Paisesm> lista = new HashMap<Integer, Paisesm>();

		String sql = "SELECT a.id,a.nombre, b.glosa as estado FROM Paises a, estados b WHERE b.id=a.Estados_id";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				Paisesm paises = new Paisesm();
				paises.setId(rs.getInt("id"));
				paises.setNombre(rs.getNString("nombre"));
				paises.setEstado(rs.getString("estado"));

				lista.put(paises.getId(), paises);

			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

		return lista;

	}

	public Paisesm getbasespaises(int cod) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Paisesm paises = new Paisesm();

		String sql = "SELECT * FROM Paises where id= " + cod;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			LocatorDao.getInstance();
			LocatorDao.getInstance();
			BasesDao bases = LocatorDao.getBasesDao();
			while (rs.next()) {

				paises.setId(rs.getInt("id"));
				paises.setNombre(rs.getNString("nombre"));
				paises.setBases(bases.getpaises(cod));
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

		return paises;

	}

	public Map<Integer, String> getpaises() {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Paisesm paises = new Paisesm();
		Map<Integer, String> lista = new HashMap<Integer, String>();

		String sql = "SELECT * FROM Paises ";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			LocatorDao.getInstance();

			while (rs.next()) {
				paises.setId(rs.getInt("id"));
				paises.setNombre(rs.getNString("nombre"));
				lista.put(paises.getId(), paises.getNombre());
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

		return lista;

	}

	public int setPais(String pais, String ruta) {

		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "INSERT INTO paises(" + " nombre," + "rutaPais,"
				+ "Estados_id) " + "values (?,?,?)";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, pais);
			pre.setString(2, ruta);
			pre.setInt(3, 1);
			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);

			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}
		return id;

	}

	public int getcodpais(String nom) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		int cod = 0;

		String sql = "SELECT * FROM Paises where nombre= ?";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, nom);
			rs = pre.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("id");
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

		return cod;

	}

	public int setEstados_id(int id) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		int cod = 0;

		String sql = "update paises set Estados_id=5 where id=? ";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.executeUpdate();

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

		return cod;

	}
	
	public Paisesm getPais(int idPais){
		Paisesm pais = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		String sql = "SELECT * FROM paises WHERE id=?";
		
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idPais);
			rs = pre.executeQuery();
			if (rs.next()){
				pais = new Paisesm();
				pais.setEstado(rs.getString("estados_id"));
				pais.setId(rs.getInt("id"));
				pais.setIdSupervisor(rs.getInt("idSupervisor"));
				pais.setNombre(rs.getString("nombre"));
				pais.setRuta(rs.getString("rutaPais"));
			}
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return pais;
	}
}
