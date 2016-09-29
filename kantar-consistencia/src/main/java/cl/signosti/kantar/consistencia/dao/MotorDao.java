package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.mysql.jdbc.Statement;

import cl.signosti.kantar.consistencia.modelo.Proyectom;
import cl.signosti.kantar.consistencia.modelo.Tareasm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.modelo.ciclom;
import cl.signosti.kantar.consistencia.modelo.TareasEjecuciones;
import cl.signosti.kantar.consistencia.modelo.ProyectoEjecuciones;

public class MotorDao extends JdbcDaoSupport implements Serializable {

	public List<Proyectom> obtenerProyectosF1(){
		String sql = "select * from proyecto p, ciclo c where p.ciclo_id = c.id and p.Estados_id <> 2 and p.Estados_id <> 3";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Proyectom> lp = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lp == null){
					lp = new ArrayList<Proyectom>();
				}
				Proyectom p = new Proyectom();
				ciclom c = new ciclom();
				c.setTipotermino(rs.getInt("tipo_termino"));
				c.setRepeticiones(rs.getInt("repeticiones"));
				c.setSe_repite(rs.getInt("serepite"));
				Date repertirH = rs.getDate("repetir_hasta");
				if(repertirH != null){
					Calendar repe = new GregorianCalendar().getInstance();
					repe.setTime(repertirH);
					c.setRepetirhastaC(repe);	
				}
				p.setFechaInicioProximoCiclo(rs.getDate("fechaInicioProximoCiclo"));
				p.setFechaIni(rs.getDate("fecha_ini"));
				p.setId(rs.getString("id"));
				p.setEstados_id(rs.getString("Estados_id"));
				p.setCiclo(c);
				lp.add(p);
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
		return lp;
	}

	public int ctdaEjecucionesProy(String id) {
		String sql = "SELECT COUNT(*) FROM proyecto_ejecuciones pj WHERE pj.proyecto_id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int c = -1;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(id));
			rs = pre.executeQuery();
			while (rs.next()) {
				c = rs.getInt(1);
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
		return c;
	}

	public void dejarProyectoInactivo(String id) {
		String sql = "update proyecto set Estados_id = 2 where id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(id));
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

	public void actualizarProximaEjecucion(Calendar fechaR, String id) {
		String sql = "update proyecto set fechaInicioProximoCiclo = ? where id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			java.sql.Date f = new java.sql.Date(fechaR.getTimeInMillis());
			pre.setDate(1, f);
			pre.setInt(2, Integer.parseInt(id));
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

	public int crearNuevaEjecucionProyecto(Calendar fechaInicio, Proyectom proyecto) {
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "INSERT INTO `proyecto_ejecuciones` (`Proyecto_id`, `fecha_inicio_produccion`, `estados_ejecucion_id`) VALUES (?, ?, ?);";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, Integer.parseInt(proyecto.getId()));
			java.sql.Date f = new java.sql.Date(fechaInicio.getTimeInMillis());
			pre.setDate(2, f);
			pre.setInt(3, 1);
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

	public List<TareasEjecuciones> obtenerTareasProyectoTemplate(Proyectom proyecto) {
		String sql = "SELECT  `id`,  `nombre`,  `idresponsable`,  `plazo`,  `tiempo`,  `idantecesora`,  `idconjunto`,  `tipo_calendario`,  `Estados_id`,  `Proyecto_id`,  `frecuencia`,  `delay`,  `tiempoDelay`,  `tiempoFrecuencia`,  `created_at`,  `updated_at` FROM `consistenciadb`.`tareas` where Proyecto_id = ?;";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<TareasEjecuciones> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(proyecto.getId()));
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt == null){
					lt = new ArrayList<TareasEjecuciones>();
				}
				TareasEjecuciones t = new TareasEjecuciones();
				t.setId(rs.getInt("id"));
				lt.add(t);
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
		return lt;
	}

	public void crearTarea(Tareasm t, int idEjecucion, Proyectom proyecto) {
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		//remplazar el sql con "?" y en el orden que corresponden.
		String sql = "INSERT INTO `tareas_ejecuciones` (`proyecto_ejecucion_id`, `Tareas_id`, `Antecesora_id`, `estados_ejecucion_id`, `inicio_estimado`, `fin_estimado`) VALUES (1, 1, 1, 1, '2015-11-04 11:45:10', '2015-11-04 11:45:16');";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, Integer.parseInt(proyecto.getId()));
			java.sql.Date f = new java.sql.Date(t.getInicioEstimadoD().getTime());
			pre.setDate(2, f);
			pre.setInt(3, 1);
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
	}

	public List<Integer> obtenerAntecesorasTarea(TareasEjecuciones tarea, Proyectom proyecto) {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Integer> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(proyecto.getId()));
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt != null){
					lt = new ArrayList<Integer>();
				}
				//Integer t = new Tareasm();
				lt.add(rs.getInt(""));
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
		return lt;
	}
//LUIS-------
	public TareasEjecuciones obtenerTareaEjecucionProyecto(TareasEjecuciones tarea, int idEjecucion, Proyectom proyecto) {
		String sql = "SELECT * FROM tareas_ejecuciones WHERE tareas_id = ? AND id = ? AND proyecto_ejecucion_id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		TareasEjecuciones t =  new TareasEjecuciones();
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, tarea.getId());
			pre.setInt(2, idEjecucion);
			pre.setInt(3, Integer.parseInt(proyecto.getId()));
			rs = pre.executeQuery();
			
			while (rs.next()) {
		
				t.setId(rs.getInt("id"));
				t.setProyecto_ejecucion_id(rs.getInt("proyecto_ejecucion_id"));
				t.setTareas_id(rs.getInt("Tareas_id"));
				t.setTiempo(rs.getString("tiempo"));
				t.setIdconjunto(rs.getInt("idconjunto"));
				t.setTipo_calendario(rs.getInt("tipo_calendario"));
				t.setEstados_ejecucion_id(rs.getInt("estados_ejecucion_id"));
				t.setFrecuencia(rs.getInt("frecuencia"));
				t.setInicio_estimado(rs.getDate("inicio_estimado"));
				t.setFin_estimado(rs.getDate("fin_estimado"));
				t.setInicio_real(rs.getDate("inicio_real"));
				t.setFin_real(rs.getDate("fin_real"));
				t.setVisible(rs.getInt("visible"));
				t.setDesviacion(rs.getDouble("desviacion"));
				t.setDesviacion_inicio(rs.getDouble("desviacion_inicio"));
				t.setDesviacion_fin(rs.getDouble("desviacion_fin"));
	
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
		return t;
	}

	public Date obtenerFechaMayorFinAntecesor(TareasEjecuciones tarea, int idEjecucion, Proyectom proyecto) {
		String sql = "SELECT";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Date fecha = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			//pre.setInt(1, Integer.parseInt(id));
			rs = pre.executeQuery();
			while (rs.next()) {
				fecha = rs.getDate(1);
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
		return fecha;
	}

	public ProyectoEjecuciones obtenerUltimoProyectoEjecucion(Proyectom proyecto) {
		String sql = "select * from proyecto_ejecuciones where Proyecto_id = ? order by 1 desc";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		ProyectoEjecuciones p =  new ProyectoEjecuciones();
		
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(proyecto.getId()));
			rs = pre.executeQuery();
			
			while (rs.next()) {
				
				p.setId(rs.getInt("id"));
				p.setProyecto_id(rs.getInt("Proyecto_id"));
				p.setMes(rs.getString("mes"));
				p.setAno(rs.getInt("ano"));
				p.setFecha_inicio_produccion(rs.getDate("fecha_inicio_produccion"));
				p.setFecha_fin_produccion(rs.getDate("fecha_fin_produccion"));
				p.setEstados_ejecucion_id(rs.getInt("estados_ejecucion_id"));
	
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
		return p;
	}

	public List<Proyectom> proyectosPorIniciarHoy() {
		String sql = "select * from proyecto p, ciclo c where p.ciclo_id = c.id and p.Estados_id <> 2";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Proyectom> lp = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lp != null){
					lp = new ArrayList<Proyectom>();
				}
				Proyectom p = new Proyectom();
				ciclom c = new ciclom();
				c.setTipotermino(rs.getInt("tipo_termino"));
				c.setRepeticiones(rs.getInt("repeticiones"));
				c.setSe_repite(rs.getInt("serepite"));
				Date repertirH = rs.getDate("repetir_hasta");
				p.setFechaInicioProximoCiclo(rs.getDate("fechaInicioProximoCiclo"));
				p.setFechaIni(rs.getDate("fecha_ini"));
				Calendar repe = new GregorianCalendar().getInstance();
				repe.setTime(repertirH);
				c.setRepetirhastaC(repe);
				p.setId(rs.getString("id"));
				p.setEstados_id(rs.getString("Estados_id"));
				p.setCiclo(c);
				lp.add(p);
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
		return lp;
	}

	public List<Tareasm> tareasSinPredecesoras(Proyectom p) {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(p.getId()));
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt != null){
					lt = new ArrayList<Tareasm>();
				}
				Tareasm t = new Tareasm();
				lt.add(t);
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
		return lt;
	}

	public void dejarTareaPendienteYVisible(Proyectom p, Tareasm t) {
		String sql = "UPDATE tareas_ejecuciones SET estados_ejecucion_id = 2, visible = 1 WHERE proyecto_ejecucion_id = ? AND Tareas_id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, Integer.parseInt(p.getId()));
			pre.setInt(2, t.getId());
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

	public List<Tareasm> tareasConPredecesorasEnEstadoCreadaDeHoy() {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt != null){
					lt = new ArrayList<Tareasm>();
				}
				Tareasm t = new Tareasm();
				lt.add(t);
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
		return lt;
	}

	public List<Tareasm> obtenerTareasPredecesorasNoFinalizadas(Tareasm tarea) {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, tarea.getId());
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt != null){
					lt = new ArrayList<Tareasm>();
				}
				Tareasm t = new Tareasm();
				lt.add(t);
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
		return lt;
	}

	public void dejarTareaEnEspera(Tareasm t) {
		String sql = "UPDATE tareas_ejecuciones SET estados_ejecucion_id = 2 WHERE id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, t.getId());
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

	public void dejarProyectoejecucionFinalizado(int idTareaEjecucion) {
		// TODO Auto-generated method stub
		String sql = "UPDATE proyecto_ejecuciones SET estados_ejecucion_id = 2 WHERE id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idTareaEjecucion);
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

	public List<Tareasm> tareasSucesoras(int idTareaEjecucion) {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idTareaEjecucion);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt != null){
					lt = new ArrayList<Tareasm>();
				}
				Tareasm t = new Tareasm();
				lt.add(t);
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
		return lt;
	}

	public void dejarTareaFinalizada(int idTareaEjecucion) {
		String sql = "UPDATE tareas_ejecuciones SET estados_ejecucion_id = 2 WHERE id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idTareaEjecucion);
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

	public List<Tareasm> tareasSucesorasAutomaticas(int idTareaEjecucion) {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> lt = null;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idTareaEjecucion);
			rs = pre.executeQuery();
			while (rs.next()) {
				if(lt != null){
					lt = new ArrayList<Tareasm>();
				}
				Tareasm t = new Tareasm();
				lt.add(t);
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
		return lt;
	}

	public boolean esUltimaTareaProyecto(int idTareaEjecucion) {
		String sql = "SELECT";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		boolean es = false;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idTareaEjecucion);
			rs = pre.executeQuery();
			while (rs.next()) {
				es = rs.getBoolean(1);
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
		return es;
	}
	
	public Usuariom obtenerDatosResponsable(Tareasm t) {
		String sql = "SELECT u.email FROM tareas AS t INNER JOIN usuarios AS u ON t.idresponsable = u.id WHERE u.id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		Usuariom usuario = new Usuariom();
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, t.getIdresponsable());
			rs = pre.executeQuery();
			
			while (rs.next()) {
				usuario.setEmail(rs.getString("email"));
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
		return usuario;
	}
	//SELECT * a tabla tareas (clase tareasm) filtrando por tarea id y proyecto id.
	public Tareasm obtenerTareaTemplate(TareasEjecuciones tarea, Proyectom proyecto) {
		String sql = "SELECT * FROM tareas WHERE id = ? AND Proyecto_id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Tareasm t =  new Tareasm();
		
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, tarea.getId());
			pre.setInt(2, Integer.parseInt(proyecto.getId()));
			rs = pre.executeQuery();
			
			while (rs.next()) {
				
				t.setId(rs.getInt("id"));
				t.setNombre(rs.getString("nombre"));
				t.setIdresponsable(rs.getInt("idresponsable"));
				t.setPlazo(rs.getInt("plazo"));
				t.setTiempoInt(rs.getInt("tiempo"));
				//t.setIdantesesora(rs.getInt("idantecesora"));
				t.setIdconjunto(rs.getInt("idconjunto"));
				t.setTipo_calendario(rs.getInt("tipo_calendario"));
				t.setEstadosId(rs.getInt("Estados_id"));
				t.setProyecto_id(rs.getInt("Proyecto_id"));
				t.setDelay(rs.getInt("delay"));
				t.setTiempodelay(Integer.valueOf(rs.getString("tiempoDelay")));
				t.setFrecuencia(rs.getInt("frecuencia"));
				t.setTiempofrecuencia(rs.getString("tiempoFrecuencia"));
				t.setTiposTareasId(rs.getInt("TiposTareas_id"));
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
		return t;
	}

	public void insertTareaEjecucion(TareasEjecuciones tarea, Proyectom proyecto, Tareasm template, int idEjecucion,
			Calendar inicioTarea, Calendar fin) {
		//INSERT INTO `tareas_ejecuciones` (`proyecto_ejecucion_id`, `Tareas_id`, `tiempo`, `idconjunto`, `tipo_calendario`, `estados_ejecucion_id`, `frecuencia`, `inicio_estimado`, `fin_estimado`) VALUES (1, 2, '1', 2, 1, 1, 1, '2016-05-03 11:10:16', '2016-06-03 11:10:18');
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		//remplazar el sql con "?" y en el orden que corresponden.
		String sql = "INSERT INTO `tareas_ejecuciones` ("
				+ "`proyecto_ejecucion_id`, "
				+ "`Tareas_id`, "
				+ "`tiempo`, "
				+ "`idconjunto`, "
				+ "`tipo_calendario`, "
				+ "`estados_ejecucion_id`, "
				+ "`frecuencia`, "
				+ "`inicio_estimado`, "
				+ "`fin_estimado`) "
				+ "VALUES (?, ?, ?, ?, ?, 1, ?, ?, ?);";
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			pre.setInt(1, idEjecucion);
			pre.setInt(2, template.getId());
			pre.setInt(3, template.getTiempo());
			pre.setInt(4, template.getIdconjunto());
			pre.setInt(5, template.getTipo_calendario());
			pre.setInt(6, template.getFrecuencia());
			pre.setDate(7, new java.sql.Date(inicioTarea.getTimeInMillis()));
			pre.setDate(8, new java.sql.Date(fin.getTimeInMillis()));
			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			logger.info("Se genero tarea ejecucion : " + id);
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
	}
	
}
