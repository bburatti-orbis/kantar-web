package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.EjecucionesCH;
import cl.signosti.kantar.consistencia.modelo.Ejecucionesm;
import cl.signosti.kantar.consistencia.utils.Close;

public class EjecucionesDao extends JdbcDaoSupport implements Serializable {
	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger.getLogger(EjecucionesDao.class);

	public int setejecucion(Ejecucionesm ejecucion) throws SQLException {
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "INSERT INTO ejecuciones(" + " fecha," + " Proceso_id,"
				+ " Bases_id," + "estadoCInterna," +"estadoCHistorica," + " periodo)"
				+ "VALUES (?,?,?,?,?,?)"
				+ "";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, ejecucion.getFecha());
			pre.setInt(2, ejecucion.getProceso());
			pre.setInt(3, ejecucion.getBase());
			pre.setInt(4, ejecucion.getCI());
			pre.setInt(5, 4);
			pre.setString(6, ejecucion.getPeriodo());

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

	public void setErrorejecucion(int idejec) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE Ejecuciones SET estadoCInterna=4" + " WHERE id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, idejec);
			pre.executeUpdate();

			pre.executeUpdate();

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

	}

	public String getFechaULtima() throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String fecha = null;
		String sql = "SELECT DATE_FORMAT(fecha,'%d/%m/%Y %H:%i' ) AS fecha FROM Ejecuciones ORDER BY id DESC LIMIT 1";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			rs = pre.executeQuery();

			while (rs.next()) {

				fecha = rs.getString("fecha");

			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}
		return fecha;

	}

	public Map<Integer, EjecucionesCH> getejecucion() throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "SELECT a.id AS ejecucion, c.nombre as pais ,c.rutaPais as ruta , b.glosa as base, a.periodo  FROM ejecuciones a, bases b, paises c WHERE estadoCHistorica=4 AND b.id=a.Bases_id AND b.Paises_id=c.id";
		Map<Integer, EjecucionesCH> consistencia = new HashMap<Integer, EjecucionesCH>();
		LocatorDao.getInstance();
		NomesclaturaDao nomen = LocatorDao.getNomenclarurasDao();

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			rs = pre.executeQuery();

			while (rs.next()) {
				EjecucionesCH ejecucion = new EjecucionesCH();

				ejecucion.setPais(rs.getString("pais"));
				ejecucion.setEjecucion(rs.getInt("ejecucion"));
				ejecucion.setPeriodo(rs.getString("periodo"));
				ejecucion.setBase(rs.getString("base"));
				ejecucion.setRuta(rs.getString("ruta")+"\\"+ejecucion.getPeriodo()+"\\");
				ejecucion.setNomenclatura(nomen.getnomenclatura(ejecucion.getEjecucion()));

				consistencia.put(ejecucion.getEjecucion(), ejecucion);
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}
		return consistencia;

	}
	
	public void setErrorejecucionCH(int idejec, int cod) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE Ejecuciones SET estadoCHistorica=?" + " WHERE id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			pre.setInt(2, idejec);
			pre.executeUpdate();

			pre.executeUpdate();

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

	}
	
	public Ejecucionesm getEjecucion(int id) throws Exception{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "SELECT e.*, p.periodo FROM ejecuciones e, bases b, periodos p WHERE e.id=? AND b.id=e.bases_id AND p.id=b.periodos_id";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, id);
			
			rs = pre.executeQuery();
			if(rs.next()){
				Ejecucionesm ejecucion = new Ejecucionesm();
				ejecucion.setBase(rs.getInt("Bases_id"));
				ejecucion.setCH(rs.getInt("estadoCHistorica"));
				ejecucion.setCI(rs.getInt("estadoCInterna"));
//				ejecucion.setCod(cod);
				ejecucion.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("fecha")));
				ejecucion.setPeriodo(rs.getString("periodo"));
//				ejecucion.setProceso(proceso);
				
				return ejecucion;
			} else {
				throw new SQLException("No existe la ejecucion solicitada");
			}
		} catch (Exception e){
			logger.error("Error de acceso a  Base de Datos", e);
			throw e;
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
	}

	public void delete(int idEjecucion) {
		Connection conn = null;
		PreparedStatement pre = null;
		String sql = "DELETE FROM ejecuciones WHERE id="+idEjecucion;

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			
			pre.executeUpdate(sql);
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(pre, conn);
			} catch (SQLException e) {

			}

		}
	}

}
