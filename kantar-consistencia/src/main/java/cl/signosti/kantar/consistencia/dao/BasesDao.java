package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.utils.Close;

public class BasesDao extends JdbcDaoSupport implements Serializable {

	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger.getLogger(BasesDao.class);

	public List<Basesm> getCliente(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Basesm> base = new ArrayList<Basesm>();
		String sql = "SELECT * FROM Bases where Clientes_id=" + cod;

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				Basesm entidad = new Basesm();
				entidad.setId(rs.getInt("id"));
				entidad.setGlosa(rs.getNString("glosa"));
				entidad.setCategoria(rs.getNString("categoria"));
				entidad.setFechaultimoproceso(rs.getNString("fechaUltProceso"));
				entidad.setFechamod(rs.getString("fechaDeModificacion"));
				entidad.setClienteid(rs.getInt("Cleintes_id"));
				entidad.setPaisid(rs.getInt("Paises_id"));
				base.add(entidad);

			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}
		}


		return base;
	}

	public List<Basesm> getpaises(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Basesm> base = new ArrayList<Basesm>();
		String sql = "SELECT * "
				+ "FROM Bases a ,Clientes b "
				+ "WHERE a.Paises_id=? AND a.Clientes_id=b.id";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			while (rs.next()) {
				Basesm entidad = new Basesm();
				entidad.setId(rs.getInt("id"));
				entidad.setGlosa(rs.getString("glosa"));
				entidad.setCategoria(rs.getString("categoria"));
				entidad.setFechaultimoproceso(rs.getString("fechaUltProceso"));
				entidad.setClienteid(rs.getInt("Clientes_id"));
				entidad.setPaisid(rs.getInt("Paises_id"));
				entidad.setNombreCliente(rs.getString("nombre"));
				base.add(entidad);

			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}

		}

		return base;
	}

	public int setbase(Basesm b) {

		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "INSERT INTO bases(" + " glosa," + "Estados_id," + "fechaDeModificacion," + "Usuarios_id,"
				+ "Paises_id) " + "values (?,?,?,?,?)";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, b.getGlosa());
			pre.setInt(2, 1);
			pre.setString(3, b.getFechamod());
			pre.setInt(4, b.getCoduser());
			pre.setInt(5, b.getPaisid());
			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);

			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}

		}
		return id;

	}

	public Map<Integer, Basesm> getlistapaises() {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Map<Integer, Basesm> lista = new HashMap<Integer, Basesm>();

		String sql = "SELECT a.id,a.glosa, b.glosa as Estados_id, DATE_FORMAT(a.fechaDeModificacion,'%Y-%m-%d  %H:%i:%s' ) as fechaDeModificacion    FROM Bases a, estados b WHERE b.id=a.estado";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				Basesm paises = new Basesm();
				paises.setId(rs.getInt("id"));
				paises.setGlosa(rs.getNString("glosa"));
				paises.setFechamod(rs.getString("fechaDeModificacion"));

				lista.put(paises.getId(), paises);

			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}

		}

		return lista;

	}

	public Basesm getBase(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Basesm base = new Basesm();

		String sql = "SELECT b.*, u.email as correo "
				+ "FROM Bases b "
					+ "LEFT JOIN usuarios u "
					+ "ON u.id=b.usuarios_id "
				+ "WHERE b.id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			while (rs.next()) {
				base.setPanel(rs.getString("panel"));
				base.setCategoria(rs.getString("categoria"));
				base.setClienteid(rs.getInt("clientes_id"));
				base.setCoduser(rs.getInt("usuarios_id"));
				base.setCorreo(rs.getString("correo"));
				base.setEstado(rs.getInt("estados_id"));
				base.setGlosa(rs.getString("glosa"));
				base.setId(rs.getInt("id"));
				base.setPaisid(rs.getInt("paises_id"));
				base.setPeriodoid(rs.getInt("periodos_id"));
			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}

		}

		return base;
	}

	public List<Basesm> getbases(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Basesm> base = new ArrayList<Basesm>();
		String sql = "SELECT * FROM Bases  where Paises_id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);

			rs = pre.executeQuery();

			while (rs.next()) {
				Basesm entidad = new Basesm();
				entidad.setId(rs.getInt("id"));
				entidad.setGlosa(rs.getString("glosa"));
				entidad.setCategoria(rs.getString("categoria"));
				entidad.setFechaultimoproceso(rs.getString("fechaUltProceso"));
				entidad.setClienteid(rs.getInt("Clientes_id"));
				entidad.setPaisid(rs.getInt("Paises_id"));
				base.add(entidad);

			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}

		}

		return base;
	}

	public void setconjunto(int cod, int id) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "update Bases set Conjunto_id=? where id=? ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			pre.setInt(2, id);
			pre.executeUpdate();

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}
		}

	}

	public List<Basesm> getbasesconjunto(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Basesm> base = new ArrayList<Basesm>();
		String sql = "SELECT * FROM Bases  where Paises_id=? and conjunto_id is null";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);

			rs = pre.executeQuery();

			while (rs.next()) {
				Basesm entidad = new Basesm();
				entidad.setId(rs.getInt("id"));
				entidad.setGlosa(rs.getString("glosa"));
				entidad.setCategoria(rs.getString("categoria"));
				// entidad.setFechaultimoproceso(rs.getString("fechaUltProceso"));
				entidad.setClienteid(rs.getInt("Clientes_id"));
				entidad.setPaisid(rs.getInt("Paises_id"));
				base.add(entidad);

			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// 
			}

		}

		return base;
	}

	public void delete(int id) {
		Connection conn = null;
		PreparedStatement pre = null;

		String sql = "DELETE FROM Bases WHERE id="+id;

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);

			pre.executeUpdate();


		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(pre, conn);
			} catch (Exception e){
				// 
			}

		}
		
	}

}
