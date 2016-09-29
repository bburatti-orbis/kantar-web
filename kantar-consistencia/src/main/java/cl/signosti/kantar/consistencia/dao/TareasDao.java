package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.Estado;
import cl.signosti.kantar.consistencia.modelo.Tareasm;
import cl.signosti.kantar.consistencia.utils.Close;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;


public class TareasDao extends JdbcDaoSupport implements Serializable{

	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger.getLogger(TareasDao.class);
	
	public int setTarea(Tareasm marca) {
		int devuelta = 0;
		
		if(marca.isAutomatica()){
			subtareas(marca);
		}
		else{
		String sql = "INSERT INTO Tareas (nombre, idresponsable, plazo, tiempo, idconjunto, tipo_calendario, proyecto_id, Estados_id, frecuencia, delay, tiempoDelay, Tiempofrecuencia) VALUES (?,?,?,?,?,?,?,6,?,?,?,?)";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pre.setString(1, marca.getNombre());
			pre.setInt(2, marca.getIdresponsable());
			pre.setInt(3, marca.getPlazo());
			pre.setObject(4, marca.getTiempo());
//			pre.setInt(5, marca.getIdantesesora()); BBM	
			
			if(marca.getIdconjunto()==null){
				pre.setString(5, null);
			}
			else{
				pre.setInt(5, marca.getIdconjunto());
			}
			
			pre.setInt(6, marca.getTipo_calendario());
			pre.setInt(7, marca.getProyecto_id());
			pre.setInt(8, marca.getFrecuencia());
			pre.setInt(9, marca.getDelay());
			pre.setObject(10, marca.getTiempodelay());
			pre.setString(11, marca.getTiempofrecuencia());
			
			pre.executeUpdate();
			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				devuelta = rs.getInt(1);

			}

			
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		
		}
		return devuelta;
	}
	
	public List<Tareasm> gettarea(int cod) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> consistencia = new ArrayList<Tareasm>();
		String sql = "SELECT "+  
				"a.id,"+
				"a.nombre  AS 'nom',"+
				"a.plazo,"+
				"COALESCE(t.descripcion, 'N/A') AS tiempo,"+
				"b.nombre AS 'encargado',"+
				"b.apellido,"+
				"a.idantecesora,"+
				"c.idP "+
			"FROM tareas a "+
			    "LEFT OUTER JOIN Usuarios b ON b.id=a.idresponsable "+
				"LEFT OUTER JOIN tareastareas c ON c.ids=a.id "+
				"LEFT OUTER JOIN tiempo t ON t.id=a.tiempo "+
			"WHERE  a.proyecto_id=? AND  a.Estados_id=1"
			+ " ORDER BY a.id ASC";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);

			rs = pre.executeQuery();

			while (rs.next()) {
				Tareasm nom = new Tareasm();

				nom.setId(rs.getInt("id"));
				nom.setNombre(rs.getString("nom"));
				nom.setPlazo(rs.getInt("plazo"));
				nom.setTiempo(rs.getInt("tiempo"));
				
				nom.setNomencargado(rs.getString("encargado")+" "+rs.getString("apellido"));
				Integer[] ids= new Integer[1];
				ids[0]=rs.getInt("idP");
				nom.setIdantesesora(ids);
				
				consistencia.add(nom);

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
	
	public List<Tareasm> gettareas(int cod, int codtarea) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Tareasm> consistencia = new ArrayList<Tareasm>();
		String sql = "SELECT a.id, a.nombre AS 'nom', a.plazo, a.tiempo, b.nombre AS 'encargado', b.apellido, a.idantecesora FROM tareas a, Usuarios b WHERE b.id=a.idresponsable AND  a.proyecto_id=? AND Estados_id=1 AND a.id <> ?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			pre.setInt(2, codtarea);

			rs = pre.executeQuery();

			while (rs.next()) {
				Tareasm nom = new Tareasm();

				nom.setId(rs.getInt("id"));
				nom.setNombre(rs.getString("nom"));
				nom.setPlazo(rs.getInt("plazo"));
				nom.setTiempo(rs.getInt("tiempo"));
				nom.setNomencargado(rs.getString("encargado")+" "+rs.getString("apellido"));
//				nom.setIdantesesora(rs.getInt("idantecesora")); //BBM
				consistencia.add(nom);

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
	
	
	
	public int setestado(int cod) {
		
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;


		String sql = "UPDATE tareas SET Estados_id=2 WHERE id=? ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, cod);
			pre.executeUpdate();

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
		
	public Tareasm gettareax(int cod) throws SQLException {

			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pre = null;
		
			String sql = "SELECT * FROM tareas WHERE id=?";
			Tareasm nom = new Tareasm();

			try {
				conn = getDataSource().getConnection();
				pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pre.setInt(1, cod);

				rs = pre.executeQuery();

				while (rs.next()) {
				
					nom.setId(rs.getInt("id"));
					nom.setNombre(rs.getString("nombre"));
					nom.setPlazo(rs.getInt("plazo"));
					nom.setIdresponsable(rs.getInt("idresponsable"));
					nom.setDelay(rs.getInt("delay"));
					nom.setTiempo(rs.getInt("tiempo"));
					nom.setFrecuencia(rs.getInt("frecuencia"));
					nom.setTiempodelay(rs.getInt("tiempoDelay"));
//					nom.setIdantesesora(rs.getInt("idantecesora")); BBM
					nom.setIdconjunto(rs.getInt("idconjunto"));
					nom.setTipo_calendario(rs.getInt("tipo_calendario"));
					nom.setProyecto_id(rs.getInt("proyecto_id"));
					nom.setEstadosId(rs.getInt("Estados_id"));
					
//					nom.setInicioEstimado(rs.getString("inicio_estimado"));
//					nom.setInicioReal(rs.getString("inicio_real"));
//					nom.setFinEstimado(rs.getString("fin_estimado"));
//					nom.setFinReal(rs.getString("fin_real"));
//					nom.setDesviacion(rs.getDouble("desviacion"));
//					
//					nom.setInicioEstimadoD(new Date(rs.getTimestamp("inicio_estimado").getTime()));
//					nom.setFinEstimadoD(new Date(rs.getTimestamp("fin_estimado").getTime()));

				}

			} catch (Exception e) {
				 logger.error("Error, causa:" , e);
			} finally {
				try {
					Close.all(rs, pre, conn);
				} catch (SQLException e) {

				}
			}
			return nom;

		}
		
		public Tareasm getTareaEjecucion(int cod) throws SQLException {

			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pre = null;
		
			String sql = "SELECT * FROM tareas_ejecucion WHERE id=?";
			Tareasm nom = null;

			try {
				conn = getDataSource().getConnection();
				pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pre.setInt(1, cod);

				rs = pre.executeQuery();

				while (rs.next()) {
					if(nom == null){
						nom = new Tareasm();
					}
					nom.setId(rs.getInt("id"));
					nom.setNombre(rs.getString("nombre"));
					nom.setPlazo(rs.getInt("plazo"));
					nom.setIdresponsable(rs.getInt("idresponsable"));
					nom.setDelay(rs.getInt("delay"));
					nom.setTiempo(rs.getInt("tiempo"));
					nom.setFrecuencia(rs.getInt("frecuencia"));
//					nom.setTiempodelay(rs.getString("tiempoDelay"));
//					nom.setIdantesesora(rs.getInt("idantecesora")); BBM
					nom.setIdconjunto(rs.getInt("idconjunto"));
					nom.setTipo_calendario(rs.getInt("tipo_calendario"));
					nom.setProyecto_id(rs.getInt("Proyecto_id"));
					nom.setEstadosId(rs.getInt("Estados_id"));
					
					nom.setInicioEstimado(rs.getString("inicio_estimado"));
					nom.setInicioReal(rs.getString("inicio_real"));
					nom.setFinEstimado(rs.getString("fin_estimado"));
					nom.setFinReal(rs.getString("fin_real"));
					nom.setDesviacion(rs.getDouble("desviacion"));
					
					nom.setInicioEstimadoD(new Date(rs.getTimestamp("inicio_estimado").getTime()));
					nom.setFinEstimadoD(new Date(rs.getTimestamp("fin_estimado").getTime()));

				}

			} catch (Exception e) {
				 logger.error("Error, causa:" , e);
				 e.printStackTrace();
			} finally {
				try {
					Close.all(rs, pre, conn);
				} catch (SQLException e) {

				}
			}
			return nom;

		}
		
		public int setUpdateTarea(Tareasm marca) {
			String sql = "UPDATE Tareas SET nombre=?, idresponsable=?, plazo=?, tiempo=?, idantecesora=?, idconjunto=?, tipo_calendario=?, Estados_id=1, frecuencia=?, delay=?, tiempodelay=? WHERE id=?";
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pre = null;
			int devuelta = 0;
			try {
				conn = getDataSource().getConnection();
				pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pre.setString(1, marca.getNombre());
				pre.setInt(2, marca.getIdresponsable());
				pre.setInt(3, marca.getPlazo());
				pre.setObject(4, marca.getTiempo());
				pre.setObject(5, marca.getIdantesesora()[0]); 
				if( marca.getIdconjunto()==null){
					pre.setString(6, null);
				} else {
					pre.setInt(6, marca.getIdconjunto());
				}
				
				pre.setInt(7, marca.getTipo_calendario());
				pre.setInt(8, marca.getFrecuencia());
				pre.setInt(9, marca.getDelay());
				pre.setObject(10, marca.getTiempodelay());
				pre.setInt(11, marca.getId());
				pre.executeUpdate();
				rs = pre.getGeneratedKeys();
				if (rs.next()) {
					devuelta = rs.getInt(1);
				}

			} catch (Exception e) {
				 logger.error("Error, causa:" , e);
			} finally {
				try {
					Close.all(rs, pre, conn);
				} catch (SQLException e) {

				}
			}
			return devuelta;
		}

		public List<Tareasm> getEjecutarProyectoXUserAndProyecto(int idUser, int proyectoId, Date inicio, Date fin) {
			List<Tareasm> lt = null;
			String sql = "SELECT "
					+ "c.id, "
					+ "c.nombre as tarea, "
					+ "c.idresponsable, "
					+ "c.plazo, "
					+ "c.tiempo, "
					+ "c.idantecesora, "
					+ "c.idconjunto, "
					+ "c.tipo_calendario, "
					+ "c.Estados_id, "
					+ "c.Proyecto_id, "
					+ "p.nombre as nombreProyecto, "
					+ "e.glosa, "
					+ "u.nombre  as nombreU, "
					+ "u.apellido, "
					+ "a.descripcion as nombreArea, "
					+ "(select sum(horasDedicadas) from bitacora_tarea bi where bi.id_tarea = c.id and id_accion in(3,5)) as horasDedicadas, "
					+ "c.inicio_estimado, "
					+ "c.inicio_real, "
					+ "c.fin_estimado, "
					+ "c.fin_real, "
					+ "c.desviacion, "
					+ "c.desviacion_inicio, "
					+ "c.desviacion_fin "
					+ "FROM tareas_ejecucion c, proyecto p, estados_ejecucion e, usuarios u, areas a "
					+ "where 1=1 "
					+ "and c.Proyecto_id = p.id "
					+ "and c.Estados_id = e.id "
					+ "and c.idresponsable = u.id "
					+ "and u.Areas_id = a.id "
					+ "and c.visible = 1 ";
			Connection conn = null;
			ResultSet r = null;
			PreparedStatement pre = null;
			try {
				conn = getDataSource().getConnection();
				
				if(idUser > 0){
					sql += " and (c.idresponsable = " + idUser;
					sql += " or (u.Areas_id = (select u2.Areas_id from usuarios u2 where u2.id = " + idUser + ") and u.Roles_id < (select u3.Roles_id from usuarios u3 where u3.id = " + idUser + ")))";
				}
				
				if(proyectoId > 0){
					sql += " and c.Proyecto_id = " + proyectoId;
				}
				if(inicio != null && fin != null){
					sql += " and c.inicio_estimado > ?";
					sql += " and c.inicio_estimado < ?";
				}
				System.out.println(sql);
				pre = conn.prepareStatement(sql);
				
				if(inicio != null && fin != null){
					pre.setDate(1, new java.sql.Date(inicio.getTime()));
					pre.setDate(2, new java.sql.Date(fin.getTime()));
				}
				
				r = pre.executeQuery();
				
				while (r.next()) {
					if(lt == null){
						lt = new ArrayList<Tareasm>();
					}
					Tareasm t = new Tareasm();
					t.setId(r.getInt("id"));
					t.setNombre(r.getString("tarea"));
					t.setIdresponsable(r.getInt("idresponsable"));
					t.setPlazo(r.getInt("plazo"));
					t.setTiempo(r.getInt("tiempo"));
//					t.setIdantesesora(r.getInt("idantecesora")); BBM
					t.setIdconjunto(r.getInt("idconjunto"));
					t.setTipo_calendario(r.getInt("tipo_calendario"));
					t.setEstadosId(r.getInt("Estados_id"));
					t.setProyecto_id(r.getInt("Proyecto_id"));
					t.setNombreProyecto(r.getString("nombreProyecto"));
					t.setEstadoS(r.getString("glosa"));
					t.setResponsable(r.getString("nombreU") + " " + r.getString("apellido"));
					t.setArea(r.getString("nombreArea"));
					t.setHorasDedicadas(r.getDouble("horasDedicadas"));
					t.setInicioEstimado(r.getString("inicio_estimado"));
					t.setInicioReal(r.getString("inicio_real"));
					t.setFinEstimado(r.getString("fin_estimado"));
					t.setFinReal(r.getString("fin_real"));
					t.setDesviacion(r.getDouble("desviacion"));
					t.setDesviacionInicio(r.getDouble("desviacion_inicio"));
					t.setDesviacionFin(r.getDouble("desviacion_fin"));
					lt.add(t);
				}

			} catch (Exception e) {
					e.printStackTrace();
				 logger.error("Error, causa:" , e);
			} finally {
				try {
					Close.all(pre, conn);
				} catch (SQLException e) {

				}
			}
			
			return lt;
		}
		
		public Estado getEstado(int estadosId) {
			Estado estado = null;
			String sql = "SELECT  `id`,  `glosa` FROM `estados` where id = ?;";
			Connection conn = null;
			ResultSet r = null;
			PreparedStatement pre = null;
			try {
				conn = getDataSource().getConnection();
				pre = conn.prepareStatement(sql);
				pre.setInt(1, estadosId);
				r = pre.executeQuery();
				if (r.next()) {
					if(estado == null){
						estado = new Estado();
					}
					estado.setId(r.getInt("id"));
					estado.setNombreEstado(r.getString("glosa"));
				}

			} catch (Exception e) {
				e.printStackTrace();
				 logger.error("Error, causa:" , e);
			} finally {
				try {
					Close.all(pre, conn);
				} catch (SQLException e) {

				}
			}
			return estado;
		}
		
		public Estado getEstadoEjecucion(int estadosId) {
			Estado estado = null;
			String sql = "SELECT  `id`,  `glosa` FROM `estados_ejecucion` where id = ?;";
			Connection conn = null;
			ResultSet r = null;
			PreparedStatement pre = null;
			try {
				conn = getDataSource().getConnection();
				pre = conn.prepareStatement(sql);
				pre.setInt(1, estadosId);
				r = pre.executeQuery();
				if (r.next()) {
					if(estado == null){
						estado = new Estado();
					}
					estado.setId(r.getInt("id"));
					estado.setNombreEstado(r.getString("glosa"));
				}

			} catch (Exception e) {
				e.printStackTrace();
				 logger.error("Error, causa:" , e);
			} finally {
				try {
					Close.all(pre, conn);
				} catch (SQLException e) {

				}
			}
			return estado;
		}

	private void subtareas(Tareasm tarea){
		
		String sql;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int antecesora=tarea.getIdantesesora()[0];
		
		try {
				conn = getDataSource().getConnection();
				int CI=Integer.parseInt(PropertiesUtil.getInstance().recuperaValor("CI"));
				int Ch=Integer.parseInt(PropertiesUtil.getInstance().recuperaValor("CH"));
				int at=Integer.parseInt(PropertiesUtil.getInstance().recuperaValor("AT"));
				sql = "Insert into Tareas (nombre,idresponsable,plazo,tiempo,idconjunto,tipo_calendario,proyecto_id,Estados_id,frecuencia,delay,tiempoDelay) values (?,?,?,?,?,?,?,?,1,?,?)";
				for(int i=0;i<3;i++){
					pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
					
					pre.setInt(2, tarea.getIdresponsable());
					if(i==0){
						pre.setString(1, tarea.getNombre()+"Consistencia Interna");
						pre.setInt(3, CI);	
					}
					if(i==1){
						pre.setString(1, tarea.getNombre()+"Consistencia Historica");
						pre.setInt(3, Ch);	
					}
					if(i==2){
						pre.setString(1, tarea.getNombre()+"Analisis de tendencia");
						pre.setInt(3, at);	
					}
					
					pre.setObject(4, tarea.getTiempo());				
					if(tarea.getIdconjunto()==null){
						pre.setString(5, null);
					}
					else{
						pre.setInt(5, tarea.getIdconjunto());
					}
					
					pre.setInt(6, tarea.getTipo_calendario());
					pre.setInt(7, tarea.getProyecto_id());
					pre.setInt(8, tarea.getFrecuencia());
					pre.setInt(9, tarea.getDelay());
					pre.setObject(10, tarea.getTiempodelay());
					pre.executeUpdate();
					rs = pre.getGeneratedKeys();
					if (rs.next()) {
						
						settareatarea( antecesora,rs.getInt(1));
						antecesora=rs.getInt(1);
						

					}
				}
				
				
				
				
				
		
					
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
	}
	
	
	private void settareatarea(int antecesora, int sucesora){
		String sql = "Insert into tareastareas (idP,idS) values (?,?)";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
				pre.setInt(1,antecesora);
				pre.setInt(2,sucesora);
				
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

}
