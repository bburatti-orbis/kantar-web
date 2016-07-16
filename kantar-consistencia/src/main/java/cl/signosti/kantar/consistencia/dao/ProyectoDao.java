package cl.signosti.kantar.consistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.ProyectoEjecucion;
import cl.signosti.kantar.consistencia.modelo.Proyectom;
import cl.signosti.kantar.consistencia.modelo.vproyecm;
import cl.signosti.kantar.consistencia.utils.Close;

public class ProyectoDao extends JdbcDaoSupport {
	private static final Logger logger = Logger.getLogger(ProyectoDao.class);

	public int setProyecto(Proyectom proyec) {

		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "INSERT INTO Proyecto (nombre, calendario, paises_id, ciclo_id, Estados_id) VALUES (?, ?, ?, ?, ?)";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, proyec.getNombre());
			pre.setInt(4, proyec.getId_ciclo());
			pre.setString(2, proyec.getCalendario());
			pre.setInt(3, Integer.parseInt(proyec.getId_paises()));
			pre.setInt(5, 3);

			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);

			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return id;

	}

	public int setestado(int cod) {

		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "UPDATE proyecto SET Estados_id=1 WHERE id=? ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			pre.executeUpdate();

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return id;

	}

	public List<vproyecm> getproyectos(int cod) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<vproyecm> list = new ArrayList<vproyecm>();

		String sql = "SELECT a.id, a.nombre, b.fecha_ini, b.serepite, b.repetircada, b.tipo_termino, b.repeticiones, b.repetir_hasta, b.repetirel, c.nombre AS 'pais' FROM proyecto a , ciclo b, paises c WHERE a.ciclo_id=b.id AND a.Paises_id=c.id AND a.Estados_id=3";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			rs = pre.executeQuery();

			while (rs.next()) {
				vproyecm proyec = new vproyecm();
				proyec.setId(rs.getInt(1));
				proyec.setNombre(rs.getString(2));
				proyec.setFecha_ini(rs.getString(3));
				;
				proyec.setSerepite(rs.getInt(4));
				proyec.setRepetircada(rs.getInt(5));
				proyec.setTipo_termino(rs.getInt(6));
				proyec.setRepeticiones(rs.getInt(7));
				proyec.setRepetir_hasta(rs.getString(8));
				proyec.setRepetirel(rs.getInt(9));
				proyec.setPais(rs.getString(10));

				list.add(proyec);

			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return list;

	}

	public int setactualizaproyecto(Proyectom proyec) {

		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "UPDATE proyecto SET Estados_id=3, nombre=?, calendario=?, paises_id=?, ciclo_id=? WHERE id=? ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, proyec.getNombre());
			pre.setInt(4, proyec.getId_ciclo());
			pre.setString(2, proyec.getCalendario());
			pre.setInt(3, Integer.parseInt(proyec.getId_paises()));
			pre.setInt(5, Integer.parseInt(proyec.getId()));
			pre.executeUpdate();

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return id;

	}

	public Proyectom getproyecto(int cod) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Proyectom proyec = new Proyectom();

		String sql = "SELECT * FROM proyecto WHERE id=?";
		LocatorDao.getInstance();

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			rs = pre.executeQuery();
			CicloDao c = LocatorDao.getCicloDao();
			while (rs.next()) {
				proyec.setId(rs.getString(1));
				proyec.setNombre(rs.getString(2));
				proyec.setCalendario(rs.getString(5));
				proyec.setId_paises(rs.getString(6));
				proyec.setId_ciclo(rs.getInt(7));
				proyec.setCiclo(c.getciclo(proyec.getId_ciclo()));

			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return proyec;

	}

	public ProyectoEjecucion getProyectoEjecucion(int cod) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		ProyectoEjecucion proyec = new ProyectoEjecucion();

		String sql = "SELECT * FROM proyecto_ejecucion WHERE id_proyecto=?";
		LocatorDao.getInstance();

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			rs = pre.executeQuery();
			//
			while (rs.next()) {
				proyec.setIdProyecto(rs.getInt(1));
				proyec.setMes(rs.getString(2));
				proyec.setAno(rs.getInt(3));
				proyec.setFechaInicioProduccionS(rs.getString(4));
				proyec.setFechaFinProduccionS(rs.getString(5));
				proyec.setFechaInicioProduccion(rs.getDate(4));
				proyec.setFechaFinProduccion(rs.getDate(5));
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return proyec;

	}

}
