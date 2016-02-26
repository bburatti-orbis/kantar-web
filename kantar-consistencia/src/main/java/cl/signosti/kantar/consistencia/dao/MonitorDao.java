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
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.PerformanceAreas;

public class MonitorDao extends JdbcDaoSupport implements Serializable {
	private static final long serialVersionUID = 4731730960391065628L;
	private static final Logger logger = Logger.getLogger(MonitorDao.class);
	
	public int[] getProyectos() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int[] proyectos= null;

		String sql = "select proyecto_id from proyecto_ejecuciones where estados_ejecucion_id = 2 order by id";
		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			rs.last();
			int[] rsIds = new int[rs.getRow()];
			rs.beforeFirst();
			int counter = 0;
			while (rs.next()) {
			  rsIds[counter] = rs.getInt(1);
			  counter++;
			}
			
			proyectos = rsIds;

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return proyectos;
	}
	
	public int[] getProyectos2() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int[] proyectos= null;

		String sql = "select id from proyecto_ejecuciones where estados_ejecucion_id = 2 order by id";
		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			rs.last();
			int[] rsIds = new int[rs.getRow()];
			rs.beforeFirst();
			int counter = 0;
			while (rs.next()) {
			  rsIds[counter] = rs.getInt(1);
			  counter++;
			}
			
			proyectos = rsIds;

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return proyectos;
	}
	
	public int getCorrelaTareas(int idProyecto) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int idMax = 0;

		String sql = "select max(tareas_id) from tareas_ejecuciones where estados_ejecucion_id = 4 and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones where estados_ejecucion_id = 2 and id = "+idProyecto+")";
		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			
			while (rs.next()) {
			  idMax = rs.getInt(1);
			}
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return idMax;
	}
	
	public int getProcentajeAvance(int idProyecto) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int porcentaje = 0, hrsEstimadas = 0, hrsFinalizadas = 0, hrsCurso = 0;
		
//		hrs estimadas totales del proyecto
		String sql = "select ("+
						"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+
						"where proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+
						"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
						"(select conversion_hrs from tiempo where id in "+
						"(select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+
						"where proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))";
		
//		hrs reales de tareas finalizadas
		String sqlA = "SELECT("+
						"(select SUM(desviacion_fin) from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 4 and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")) + "+
						"(select ("+
						"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 4  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
						"(select conversion_hrs from tiempo where id in ("+
						"select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 4  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))))";
		System.out.println(sqlA);
	 
//		hrs reales de tareas en curso
	 	String sqlB = "SELECT("+
						"(select SUM(desviacion) from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")) + "+
						"(select ("+
						"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 2  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
						"(select conversion_hrs from tiempo where id in ("+
						"select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 2  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))))"; 
		
		System.out.println(sqlB);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			
			while (rs.next()) {
				hrsEstimadas = rs.getInt(1);
			}
			pre.close();
			rs.close();
			
			pre = conn.prepareStatement(sqlA);
			rs = pre.executeQuery();
			while (rs.next()) {
				hrsFinalizadas = rs.getInt(1);
			}
			pre.close();
			rs.close();
			
			pre = conn.prepareStatement(sqlB);
			rs = pre.executeQuery();
			while (rs.next()) {
				hrsCurso = rs.getInt(1);
			}
			if(hrsEstimadas != 0){
				porcentaje = (hrsFinalizadas+hrsCurso)/hrsEstimadas;
			}
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return porcentaje;
	}
	
	public int getTareasAtrasadas(int idProyecto) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int atrasadas = 0;

		String sql = "select count(*) from tareas_ejecuciones where estados_ejecucion_id = 2 and fin_real > fin_estimado or fin_estimado < now() and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones where estados_ejecucion_id = 2 and id = "+idProyecto+")";
		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			while (rs.next()) {
				atrasadas = rs.getInt(1);
			}
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return atrasadas;
	}
	
	public int getFFR(int idProyecto) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int ffrA = 0,ffrB = 0, ffrC = 0,ffr = 0;

		String sqlA = "SELECT("+
					"(select SUM(desviacion_fin) from tareas_ejecuciones "+ 
					"where estados_ejecucion_id = 4 and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
					"where estados_ejecucion_id = 2 and id = "+idProyecto+")) + "+
					"(select ("+
					"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
					"where estados_ejecucion_id = 4  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
					"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
					"(select conversion_hrs from tiempo where id in ("+
					"select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
					"where estados_ejecucion_id = 4  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
					"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))))";
		 System.out.println(sqlA);
		 
		 String sqlB = "SELECT("+
						"(select SUM(desviacion) from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")) + "+
						"(select ("+
						"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 2  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
						"(select conversion_hrs from tiempo where id in ("+
						"select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id = 2  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))))";
		 
		 String sqlC = "select ("+
						"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id not in (2,4)  and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
						"(select conversion_hrs from tiempo where id in ("+
						"select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where estados_ejecucion_id not in (2,4) and proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+ 
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sqlA);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			while (rs.next()) {
				ffrA = rs.getInt(1);
			}
			pre.close();
			rs.close();
			
			pre = conn.prepareStatement(sqlB);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			while (rs.next()) {
				ffrB = rs.getInt(1);
			}
			pre.close();
			rs.close();
			
			pre = conn.prepareStatement(sqlC);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			while (rs.next()) {
				ffrC = rs.getInt(1);
			}
			
			ffr = ffrA+ffrB+ffrC;
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return ffr;
	}
	
	public int getFFE(int idProyecto) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int ffe = 0;
		String sql = "select ("+
						"(select plazo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+
//						cambio testing dani---> agregar id proyecto
						"where estados_ejecucion_id = 2 and id = "+idProyecto+"))) * "+
						"(select conversion_hrs from tiempo where id in ( "+
						"select tiempo from tareas where id in (select tareas_id from tareas_ejecuciones "+ 
						"where proyecto_ejecucion_id in (select proyecto_id from proyecto_ejecuciones "+
//						cambio testing dani---> agregar id proyecto
						"where estados_ejecucion_id = 2 and id = "+idProyecto+")))))";
		 System.out.println(sql);
		 
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			while (rs.next()) {
				ffe = rs.getInt(1);
			}
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return ffe;
	}
	
	public List<PerformanceAreas> getPerformance() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<PerformanceAreas> lp = null;
		
		String sql = "select u.Areas_id, a.descripcion ,t.estados_ejecucion_id, ee.glosa, "+ 
						"("+
						"select count(*) "+ 
						"from tareas_ejecuciones tt"+
						"	join proyecto_ejecuciones tpe"+ 
						"		on tt.proyecto_ejecucion_id = tpe.id"+
						"	join tareas tt2 "+
						"		on tt2.id = tt.Tareas_id"+
						"	join usuarios tu "+
						"		on tu.id = tt2.idresponsable"+
						"	join estados_ejecucion tee "+
						"		on tee.id = tt.estados_ejecucion_id"+
						"	join areas ta "+
						"		on ta.id = tu.Areas_id "+
						"where tpe.id = pe.id "+
						"and ta.id = a.id "+
						") as suma "+
						"from tareas_ejecuciones t "+
						"	join proyecto_ejecuciones pe "+ 
						"		on t.proyecto_ejecucion_id = pe.id"+
						"	join tareas t2 "+
						"		on t2.id = t.Tareas_id"+
						"	join usuarios u "+
						"		on u.id = t2.idresponsable"+
						"	join estados_ejecucion ee"+ 
						"		on ee.id = t.estados_ejecucion_id"+
						"	join areas a "+
						"		on a.id = u.Areas_id "+
						"where pe.estados_ejecucion_id = 2 "+
						"and t.estados_ejecucion_id not in (1,4) "+
						"group by u.Areas_id, t.estados_ejecucion_id";
		 System.out.println(sql);
		 
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			
			while (rs.next()) {
				if(lp == null){
					lp = new ArrayList<PerformanceAreas>();
				}
				PerformanceAreas pa = new PerformanceAreas();
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getInt(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getInt(5));
				pa.setAreaId(rs.getInt(1));
				pa.setAreaGlosa(rs.getString(2));
				pa.setEstadoId(rs.getInt(3));
				pa.setEstadoGlosa(rs.getString(4));
				pa.setTotal(rs.getInt(5));
				lp.add(pa);
			}
			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

	public List<cl.signosti.kantar.consistencia.modelo.Proyectom> getProyectosV2() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<cl.signosti.kantar.consistencia.modelo.Proyectom> proyectos= null;

		String sql = "select pe.proyecto_id, CONCAT(p.nombre, ' (', pe.id, ')') as nombre from proyecto_ejecuciones pe inner join proyecto p on pe.Proyecto_id = p.id where estados_ejecucion_id = 2 order by pe.id";
		System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			LocatorDao.getInstance();
			while (rs.next()) {
				if(proyectos == null){
					proyectos = new ArrayList<cl.signosti.kantar.consistencia.modelo.Proyectom>();
				}
				cl.signosti.kantar.consistencia.modelo.Proyectom p = new cl.signosti.kantar.consistencia.modelo.Proyectom();
				p.setId(rs.getString("proyecto_id"));
				p.setNombre(rs.getString("nombre"));
				proyectos.add(p);
			}
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
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

		return proyectos;
	}
	
}
