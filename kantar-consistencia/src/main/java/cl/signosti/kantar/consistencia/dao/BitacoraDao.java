package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.AccionBitacora;
import cl.signosti.kantar.consistencia.modelo.Area;
import cl.signosti.kantar.consistencia.modelo.Bitacora;
import cl.signosti.kantar.consistencia.modelo.Usuariom;

public class BitacoraDao extends JdbcDaoSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6385487314440633597L;
	private static final Logger logger = Logger.getLogger(BitacoraDao.class);
	
	public void ingresarBitacora(Bitacora bita) {
		String sql = "INSERT INTO `bitacora_tarea` ("
				+ "`id_tarea`, "
				+ "`id_accion`, "
				+ "`fechaEstimadaFinTarea`, "
				+ "`hora`, "
				+ "`minutos`, "
				+ "`horasDedicadas`, "
				+ "`comentarios`,"
				+ "idUsuario,"
				+ "cl,"
				+ "avance) VALUES ("
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?)";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, bita.getIdTarea());
			pre.setInt(2, bita.getIdAccion());
			pre.setString(3, bita.getFechaEstimadaFinTareaS());
			pre.setInt(4, bita.getHoras());
			pre.setInt(5, bita.getMinutos());
			pre.setDouble(6, bita.getHorasDedicadas());
			pre.setString(7, bita.getComentarios());
			pre.setInt(8, bita.getIdUsuario());
			pre.setInt(9, bita.getCl());
			pre.setInt(10, bita.getAvance());
			pre.executeUpdate();
			logger.debug("Ingreso de bitacora exitoso");
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
	}

	public void cerrarConexion(ResultSet rs, PreparedStatement pre, Connection conn){
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

	public List<Bitacora> getBitacoraTarea(int idTarea) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Bitacora> lb = null;
		String sql = "SELECT  "
				+ "b.`id_tarea`,  "
				+ "b.`id_bitacora`,  "
				+ "b.`id_accion`,  "
				+ "b.`fechaEstimadaFinTarea`,  "
				+ "b.`hora`,  "
				+ "b.`minutos`,  "
				+ "b.`horasDedicadas`,  "
				+ "b.`comentarios`,  "
				+ "b.`fechaRegistro`, "
				+ "b.idUsuario, "
				+ "ab.nombre_accion, "
				+ "u.nombre as nombre_usuario, "
				+ "u.apellido as apellido_usuario, "
				+ "b.cl, "
				+ "b.avance "
				+ "FROM `bitacora_tarea` b, usuarios u, acciones_bitacora ab where "
				+ "id_tarea = ?"
				+ " and b.idUsuario = u.id "
				+ " and b.id_accion = ab.id_accion"
				+ " order by b.`fechaRegistro` desc;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idTarea);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lb == null){
					lb = new ArrayList<Bitacora>();
				}
				Bitacora b=new Bitacora();
				b.setIdTarea(rs.getInt("id_tarea"));
				b.setIdBitacora(rs.getInt("id_bitacora"));
				b.setIdAccion(rs.getInt("id_accion"));
				b.setFechaEstimadaFinTareaS(rs.getString("fechaEstimadaFinTarea"));
				b.setFechaEstimadaFinTareaD(rs.getDate("fechaEstimadaFinTarea"));
				b.setHoras(rs.getInt("hora"));
				b.setMinutos(rs.getInt("minutos"));
				b.setHorasDedicadas(rs.getDouble("horasDedicadas"));
				b.setComentarios(rs.getString("comentarios"));
				b.setFechaRegistro(rs.getDate("fechaRegistro"));
				b.setFechaRegistroS(rs.getString("fechaRegistro"));
				b.setIdUsuario(rs.getInt("idUsuario"));
				b.setAccion(rs.getString("nombre_accion"));
				b.setActor(rs.getString("nombre_usuario") + " " + rs.getString("apellido_usuario"));
				b.setAvance(rs.getInt("avance"));
				b.setCl(rs.getInt("cl"));
				lb.add(b);
			}

		} catch (Exception e) {
			 e.printStackTrace();
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
		return lb;
	}

	public List<AccionBitacora> getAccionBitacora(int idEstadoTarea) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<AccionBitacora> lb = null;
		String sql = "SELECT  `id_accion`,  `nombre_accion`,  `estado_inicial`,  `estado_final` FROM `acciones_bitacora` where estado_inicial in (0,?);";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idEstadoTarea);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lb == null){
					lb = new ArrayList<AccionBitacora>();
				}
				AccionBitacora b=new AccionBitacora();
				b.setIdAccion(rs.getInt("id_accion"));
				b.setNombreAccion(rs.getString("nombre_accion"));
				b.setEstadoInicial(rs.getInt("estado_inicial"));
				b.setEstadoFinal(rs.getInt("estado_final"));
				lb.add(b);
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
		return lb;
	}

	public void actualizarEstadoTarea(Bitacora bita) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE `tareas_ejecucion` SET `Estados_id`=(SELECT  `estado_final` FROM `acciones_bitacora` where `id_accion` = ?) WHERE `id`=?;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, bita.getIdAccion());
			pre.setInt(2, bita.getIdTarea());
			pre.executeUpdate();
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
	}

	public List<Area> getAreas() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Area> lb = null;
		String sql = "SELECT  `id`,  `descripcion`,  `responsable`,  `created_at`,  `updated_at` FROM `areas`;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lb == null){
					lb = new ArrayList<Area>();
				}
				Area b=new Area();
				b.setIdArea(rs.getInt("id"));
				b.setDesc(rs.getString("descripcion"));
				b.setResponsable(rs.getInt("responsable"));
				lb.add(b);
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
		return lb;
	}

	public List<Usuariom> getUserPorArea(int idArea) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Usuariom> lb = null;
		String sql = "SELECT  `id`,  `email`,  `usuario`,  `nombre`,  `apellido`,  `password`,  `Areas_id`,  `Roles_id`,  `created_at`,  `updated_at` FROM `usuarios` where Areas_id = ?;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idArea);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lb == null){
					lb = new ArrayList<Usuariom>();
				}
				Usuariom b=new Usuariom();
				b.setId(rs.getInt("id"));
				b.setNombre(rs.getString("nombre"));
				b.setApellido(rs.getString("apellido"));
				lb.add(b);
			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
		return lb;		
	}

	
	public void actualizarResponsable(Bitacora bita) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE `tareas_ejecucion` SET `idresponsable`=? WHERE  `id`=?;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, bita.getHoras());
			pre.setInt(2, bita.getIdTarea());
			pre.executeUpdate();
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
	}

	public void actualizarDesviacionInicio(Bitacora bita, int diff) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE `tareas_ejecucion` SET `desviacion_inicio`=?, `desviacion`=?, inicio_real = CURRENT_TIMESTAMP() WHERE  `id`=?;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, diff);
			pre.setInt(2, diff);
			pre.setInt(3, bita.getIdTarea());
			pre.executeUpdate();
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
		
	}

	public void actualizarDesviacionFin(Bitacora bita, int diff) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE `tareas_ejecucion` SET `desviacion_fin`=?, `desviacion`=?, fin_real = CURRENT_TIMESTAMP() WHERE  `id`=?;";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, diff);
			pre.setInt(2, diff);
			pre.setInt(3, bita.getIdTarea());
			pre.executeUpdate();
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			cerrarConexion(rs, pre, conn);
		}
	}
	
}
