package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Aream;
import cl.signosti.kantar.consistencia.modelo.DetalleNomesclm;
import cl.signosti.kantar.consistencia.modelo.Hoja1_2;
import cl.signosti.kantar.consistencia.modelo.Hoja1m;
import cl.signosti.kantar.consistencia.modelo.Hoja2m;
import cl.signosti.kantar.consistencia.modelo.ListadoProyectos;
import cl.signosti.kantar.consistencia.modelo.ReportNomenm;
import cl.signosti.kantar.consistencia.modelo.ReportePerformance;
import cl.signosti.kantar.consistencia.modelo.ReportesRevisarProyecto;
import cl.signosti.kantar.consistencia.modelo.ResutadoGeneralm;
import cl.signosti.kantar.consistencia.utils.Funcionesvarias;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;

public class ReportesDao extends JdbcDaoSupport implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -483909151431734110L;
	private static final Logger logger = Logger.getLogger(ReportesDao.class);

	public Map<Integer, ResutadoGeneralm> getResultGeneral(int inc, int limit, String desde, String hasta) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String link= PropertiesUtil.getInstance().recuperaValor("ruta_informes");
		Map<Integer, ResutadoGeneralm> lista = new HashMap<Integer, ResutadoGeneralm>();

		String sql = "SELECT p.nombre,"
				   +"b.glosa,"
				   +"COALESCE(b.categoria, 'No Asignada') AS 'descripcion',"
				   +"COALESCE(c.nombre,'Cliente no asignado') AS 'cliente',"
				   +"u.nombre AS 'nom',"
				   +"u.apellido,"
				   +"r.periodo,"
				   +"b.id AS 'idbase',"
				   +"b.estadoCI AS 'CI',"
				   +"b.estadoCH AS 'CH',"
				   +"e.created_at AS 'fecha_inicio',"
				   +"e.fechaTermino AS 'fecha_termino' ,"
				   +"e.estadoCInterna,"
				   +"e.estadoCHistorica,"
				   +"e.id, "
				   +"COALESCE(aI.id, 0) AS 'autorizacionInterna', "
				   +"COALESCE(aH.id, 0) AS 'autorizacionHistorica' "
			+"FROM ejecuciones e " 
                 +"LEFT OUTER JOIN bases b ON e.bases_id = b.id "
                 +"LEFT OUTER JOIN paises p ON b.paises_id = p.id "
				 +"LEFT OUTER JOIN periodos r ON r.id = b.periodos_id "
  				 +"LEFT OUTER JOIN usuarios u ON b.usuarios_id = u.id "
                 +"LEFT OUTER JOIN clientes c ON b.clientes_id = c.id "
                 +"LEFT OUTER JOIN autorizadas aI ON (aI.Ejecuciones_id = e.id && aI.proceso = 0) "
                 +"LEFT OUTER JOIN autorizadas aH ON (aH.Ejecuciones_id = e.id && aH.proceso = 1) "
  			+"WHERE e.fechaTermino >= ? && e.fechaTermino <= ? "
            +"ORDER BY e.fechaTermino DESC "
            +"LIMIT ?,?";

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			Date parsed = format.parse(desde);
			pre.setDate(1, new java.sql.Date(parsed.getTime()));
			parsed = format.parse(hasta);
			pre.setDate(2, new java.sql.Date(parsed.getTime() + 24*60*60*1000));
			pre.setInt(3, inc);
			pre.setInt(4, limit);
			rs = pre.executeQuery(); 

			int k = 0;
			while (rs.next()) {
				ResutadoGeneralm result = new ResutadoGeneralm();

				result.setId(rs.getString("id"));
				result.setNombre(rs.getString("nombre"));
				result.setGlosa(rs.getString("glosa"));
				result.setDescripcion(rs.getString("descripcion"));
				result.setCliente(rs.getString("cliente"));
				result.setFecha_ini(rs.getString("fecha_inicio"));
				result.setFecha_ter(rs.getString("fecha_termino"));
				result.setPeriodo(rs.getString("periodo"));
				result.setIdBases(rs.getString("idbase"));
				int ci=rs.getInt("estadoCInterna");
				int ch= rs.getInt("estadoCHistorica");
				
				
				String estadoCI = rs.getString("CI");
				if(estadoCI.equals("4")){
					int autorizada = rs.getInt("autorizacionInterna");
					if(autorizada != 0){
						result.setCI("AUTORIZADA");
					} else {
						result.setCI("ERRONEA");
					}
				}
				else{
					result.setCI("OK");
				}
				String estadoCH = rs.getString("CH");
				if(estadoCH.equals("4")){
					int autorizada = rs.getInt("autorizacionHistorica");
					if(autorizada != 0){
						result.setCH("AUTORIZADA");
					} else {
						result.setCH("ERRONEA");
					}
				}
				else{
					result.setCH("OK");
				}
									
				
				if(ci == 103 && ch == 109){
					result.setEstado("ENTREGAR");
				} else if(ci==102 && ch==108){
					result.setEstado("TERMINADA");
					if(!estadoCI.equals("4") || !estadoCH.equals("4")){
						result.setEstado("REVISAR");
					}
				} else {
					result.setEstado("EN PROCESO");
				}
				result.setUsuario(rs.getString("nom")+ " "+ rs.getString("apellido"));
				
				result.setLink(link+"?ruta="+result.getNombre()+"\\"+result.getPeriodo()+"\\"+result.getGlosa()+"\\"+result.getGlosa()+".xlsx");

				lista.put(k, result);
				k++;

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

		return lista;

	}

	public Map<Integer, ReportNomenm> getnomenclatura(int cod)
			throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Map<Integer, ReportNomenm> lista = new HashMap<Integer, ReportNomenm>();

		String sql = "SELECT n.glosa AS 'nomesclatura', "
				+ "n.tipo AS 'tipo',"
				+ "n.estadoCInterna AS 'estado',"
				+ "p.nombre AS 'pais',"
				+ "b.glosa AS 'base',"
				+ "n.id AS 'idnom' ,"
				+ "DATE_FORMAT(e.fecha,'%Y-%m-%d' ) AS 'fecha',"
				+ "COALESCE( (SELECT (SUM(valor)-SUM(sumaHijos)) FROM marcas WHERE estadoCInterna=4 AND Nomenclatura_id=n.id ),'0' ) AS diferencia "
				+ "FROM bases b, ejecuciones e, nomenclaturas n , paises p "
				+ "WHERE b.id=? "
				+ "  AND n.bases_id=b.id "
				+ "  AND p.id=b.Paises_id "
				+ "  AND e.bases_id=b.id ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			int k = 0;
			while (rs.next()) {
				ReportNomenm result = new ReportNomenm();

				result.setNomenclatura(rs.getString("nomesclatura"));
				result.setTipo(rs.getString("tipo"));
				int es=rs.getInt("estado");
				if(es==3){
					result.setEstado("OK");
				}
				else if(es==4){
					result.setEstado("ERRONEA");
				}

				result.setPais(rs.getString("pais"));
				result.setBase(rs.getString("base"));
				result.setFecha(rs.getString("fecha"));
				result.setDiferencia(rs.getString("diferencia"));
				result.setId_nom(rs.getString("idnom"));

				lista.put(k, result);
				k++;

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

		return lista;

	}

	public Map<Integer, DetalleNomesclm> getdetallenom(int cod)
			throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Funcionesvarias fun= new Funcionesvarias();
		Map<Integer, DetalleNomesclm> lista = new HashMap<Integer, DetalleNomesclm>();

		String sql = "SELECT 	m.glosa AS glosa,"+ 
					"m.linea, "+
					"m.nivel, "+
					"m.valor AS total_informado, "+
					"COALESCE( (m.sumaHijos), m.valor ) AS total_calculado, "+
					"e.glosa AS estadoCI,"+
					"n.glosa AS nomenclatura, "+
					"n.tipo AS IT2, "+
					"DATE_FORMAT(j.fecha,'%Y-%m-%d' ) AS fecha, "+
					"b.glosa AS base,"+
					"p.nombre AS pais  "+
			"FROM   marcas m "+
			"LEFT OUTER JOIN nomenclaturas n ON n.id= m.nomenclatura_id "+
			"LEFT OUTER JOIN bases b ON b.id= n.Bases_id "+
			"LEFT OUTER JOIN ejecuciones j  ON j.Bases_id=b.id "+
			"LEFT OUTER JOIN paises p ON p.id= b.Paises_id "+
			"LEFT OUTER JOIN estados e ON e.id= m.estadoCInterna "+
			"WHERE n.id=? "+
			"ORDER BY m.linea ASC";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			int k = 0;
			while (rs.next()) {
				DetalleNomesclm result = new DetalleNomesclm();

				
				result.setNivel(rs.getString("nivel"));
				String n=fun.repeat(" ",rs.getInt("nivel"));
				result.setLinea(n+rs.getString("linea"));
				
				result.setTotalInformado(rs.getString("total_informado"));
				result.setTotalCalculado(rs.getString("total_calculado"));
				result.setEstado(rs.getString("estadoCI"));
				result.setNomenclatura(rs.getString("nomenclatura"));
				result.setTipo_nomencl(rs.getString("IT2"));
				result.setFecha(rs.getString("fecha"));
				result.setBase(rs.getString("base"));
				result.setPais(rs.getString("pais"));

				lista.put(k, result);
				k++;

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

		return lista;

	}

	public Map<Integer, Hoja1m> hoja1(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Map<Integer, Hoja1m> lista = new HashMap<Integer, Hoja1m>();

		String sql = "select a.id, DATE_FORMAT(a.fecha_inicio,'%Y-%m-%d %H:%i:%s' ) as fecha_inicio ,DATE_FORMAT(a.fecha_fin,'%Y-%m-%d %H:%i:%s' ) as fecha_fin, (select count(id) from ejecuciones where Proceso_id=a.id ) as 'cantidad',(select count(Bases_id) from ejecuciones where estadoCInterna=3) as 'basesOK', (select count(Bases_id) from ejecuciones where estadoCInterna=4) as 'basesNOK' from proceso a where a.id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			int k = 0;
			while (rs.next()) {
				Hoja1m result = new Hoja1m();

				result.setId(rs.getInt("id"));
				result.setFecha_ini(rs.getString("fecha_inicio"));
				result.setFecha_ter(rs.getString("fecha_fin"));
				result.setCanbases(rs.getInt("cantidad"));
				result.setBasesOK(rs.getInt("basesOK"));
				result.setBasesNOK(rs.getInt("basesNOK"));

				lista.put(k, result);
				k++;

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

		return lista;

	}

	public Map<Integer, Hoja1_2> hoja1_2(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Map<Integer, Hoja1_2> lista = new HashMap<Integer, Hoja1_2>();

		String sql = "select c.nombre as pais,b.glosa as base,COALESCE( b.categoria,'No asignada') as 'categoria' , d.nombre,d.apellido,COALESCE( (select glosa from estados where id =  a.estadoCInterna) ,'No asignada')  as CI, COALESCE( (select glosa from estados where id =  a.estadoCHistorica) ,'No Realizada') as CH , a.periodo, a.periodo as 'Periodoanterior',(select glosa from estados where id = b.estado)  as origen from ejecuciones a , bases b, paises c, usuarios d where  a.id=? and a.bases_id=b.id and c.id=b.paises_id and d.id=b.usuarios_id";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			int k = 0;
			while (rs.next()) {
				Hoja1_2 result = new Hoja1_2();

				result.setPais(rs.getString("pais"));
				result.setBase(rs.getString("base"));
				result.setCategoria(rs.getString("categoria"));
				result.setResponsable(rs.getString("nombre") + " "
						+ rs.getString("apellido"));
				result.setCI(rs.getString("CI"));
				result.setCH(rs.getString("CH"));
				result.setPeriodoAc(rs.getString("periodo"));
				result.setPeriodoAnt(rs.getString("Periodoanterior"));
				result.setOrigen(rs.getString("origen"));

				lista.put(k, result);
				k++;

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

		return lista;

	}

	public Map<Integer, Hoja2m> hoja2(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		Map<Integer, Hoja2m> lista = new HashMap<Integer, Hoja2m>();

		String sql = "select d.nombre as pais,b.glosa as base, a.periodo,COALESCE( (a.estadoCHistorica), 'No Ejecutada' ) as CH, (select glosa from estados where id= b.estado) as estado, c.glosa as nomenclatura , c.tipo,e.glosa, e.linea, e.nivel,e.valor as totalinformado,COALESCE( (e.sumaHijos), e.valor ) as total_calculado,COALESCE( (e.valor-e.sumaHijos),'0' ) as diferencia from ejecuciones a, bases b, nomenclaturas c , paises d, marcas e, proceso f  where a.estadoCInterna = 4 and b.id=a.bases_id  and e.nomenclatura_id= c.id and f.id=a.Proceso_id and a.id=? order by d.nombre,c.glosa, e.linea";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();

			int k = 0;
			while (rs.next()) {
				Hoja2m result = new Hoja2m();
				result.setPais(rs.getString("pais"));
				result.setBase(rs.getString("base"));
				result.setPeriodo(rs.getString("periodo"));
				result.setEstado(rs.getString("estado"));
				result.setNomenclatura(rs.getString("nomenclatura"));
				if (rs.getString("tipo").equals("2")) {
					result.setTipo("IT2");
				} else {
					result.setTipo("IT0");
				}
				result.setCH(rs.getString("CH"));
				result.setGlosa(rs.getString("glosa"));
				result.setLinea(rs.getInt("linea"));
				result.setNivel(rs.getInt("nivel"));
				result.setTotalinformado(rs.getInt("totalinformado"));
				result.setTotalcalculado(rs.getInt("total_calculado"));
				result.setDiferencia(rs.getInt("diferencia"));

				lista.put(k, result);
				k++;

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

		return lista;

	}
	
	public int[] getCodigosCH(int cod,String nomenclatura,String periodo,String base) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		int[] vuelta= new int[2];

		String sql = "select a.id, b.id as 'ejecucion'  from Nomenclaturas a , ejecuciones b, proceso c, bases d "
				+ "where "
				+ "a.glosa=? "
				+ "and b.periodo=? "
				+ "and c.id=? "
				+ "and d.glosa=? "
				+ "and b.Bases_id=d.id "
				+ "and a.Ejecuciones_id=b.id ";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1,nomenclatura);
			pre.setString(2, periodo);
			pre.setInt(3, cod);
			pre.setString(4, base);
			
			rs = pre.executeQuery();

			while (rs.next()) {
				
				vuelta[0]=rs.getInt("ejecucion");
				vuelta[1]=rs.getInt("id");
						

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

		return vuelta;

	}
	
	
	
	public List<Aream> getareas() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;



		String sql = "Select id,descripcion,responsable from areas";
		List<Aream> listarea= new ArrayList<Aream>();
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			
			rs = pre.executeQuery();

			while (rs.next()) {
				Aream area= new Aream();
				area.setId(rs.getInt(1));
				area.setDescripcion(rs.getString(2));
				area.setResponsable(rs.getInt(3));
				listarea.add(area);			

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

		return listarea;

	}
	
	public List<ReportePerformance> getReportePerformance() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ReportePerformance> lr = null;
		
		String sql = "SELECT p.nombre AS proyecto, pa.nombre AS pais, a.descripcion AS AREA, u.nombre AS ejecutivo, "+
						"(SELECT COUNT(*) "+
						"FROM tareas_ejecuciones ste "+
						"JOIN proyecto_ejecuciones spe ON ste.proyecto_ejecucion_id = spe.id "+
						"JOIN tareas sta ON ste.Tareas_id = sta.id "+
						"JOIN usuarios su ON sta.idresponsable = su.id "+
						"WHERE ste.estados_ejecucion_id = 4 AND spe.id = pe.id AND su.id = u.id) "+ 
						"AS tareas_realizadas, "+
						"(SELECT COUNT(*) "+
						"FROM tareas_ejecuciones ste "+
						"JOIN proyecto_ejecuciones spe ON ste.proyecto_ejecucion_id = spe.id "+
						"JOIN tareas sta ON ste.Tareas_id = sta.id "+
						"JOIN usuarios su ON sta.idresponsable = su.id "+
						"WHERE spe.id = pe.id AND su.id = u.id) "+
						"AS tareas_totales, "+
						"(select "+
						"	((SELECT COUNT(*)"+
						"	FROM tareas_ejecuciones ste"+
						"	JOIN proyecto_ejecuciones spe ON ste.proyecto_ejecucion_id = spe.id"+
						"	JOIN tareas sta ON ste.Tareas_id = sta.id"+
						"	JOIN usuarios su ON sta.idresponsable = su.id"+
						"	WHERE ste.estados_ejecucion_id = 4 AND spe.id = pe.id AND su.id = u.id"+
						"		)*100/(SELECT COUNT(*)"+
						"				FROM tareas_ejecuciones ste"+
						"				JOIN proyecto_ejecuciones spe ON ste.proyecto_ejecucion_id = spe.id"+
						"				JOIN tareas sta ON ste.Tareas_id = sta.id"+
						"				JOIN usuarios su ON sta.idresponsable = su.id"+
						"				WHERE spe.id = pe.id AND su.id = u.id))"+
						") as porc "+
					"FROM proyecto p "+
					"JOIN proyecto_ejecuciones pe ON p.id = pe.Proyecto_id "+
					"JOIN paises pa ON p.Paises_id = pa.id "+
					"JOIN tareas_ejecuciones te ON te.proyecto_ejecucion_id = pe.id "+
					"JOIN tareas ta ON te.Tareas_id = ta.id "+
					"JOIN usuarios u ON ta.idresponsable = u.id "+
					"JOIN areas a ON a.id = u.Areas_id "+
					"WHERE pe.estados_ejecucion_id = 2";
		 System.out.println(sql);
		 
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			
			while (rs.next()) {
				if(lr == null){
					lr = new ArrayList<ReportePerformance>();
				}
				ReportePerformance rp = new ReportePerformance();
				rp.setNombreProyecto(rs.getString(1));
				rp.setPais(rs.getString(2));
				rp.setArea(rs.getString(3));
				rp.setNombreEjecutivo(rs.getString(4));
				rp.setTareasRealizadas(rs.getInt(5));
				rp.setTareasTotales(rs.getInt(6));
				rp.setPorcParticipacion(rs.getLong(7));
				lr.add(rp);
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
		return lr;
	}
	
	public List<ListadoProyectos> getListadoProyectos() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ListadoProyectos> lp = null;
		
		String sql = "SELECT p.nombre, date_format(pe.fecha_inicio_produccion, '%d/%c/%Y'), pe.id FROM proyecto_ejecuciones pe JOIN proyecto p ON p.id = pe.Proyecto_id WHERE pe.estados_ejecucion_id = 2 order by pe.id";
		 System.out.println(sql);
		 
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			
			while (rs.next()) {
				if(lp == null){
					lp = new ArrayList<ListadoProyectos>();
				}
				ListadoProyectos rp = new ListadoProyectos();
				rp.setNombre(rs.getString(1));
				rp.setFechaInicio(rs.getString(2));
				rp.setId(rs.getInt(3));
				lp.add(rp);
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
	
	public List<ReportesRevisarProyecto> getDetalleEjecucion(int idProyecto) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ReportesRevisarProyecto> lp = null;
		
		String sql = "SELECT p.nombre, t.nombre, a.descripcion, u.nombre, ee.glosa, te.inicio_estimado, te.inicio_real, te.fin_estimado,"+ 
						"("+
						"	select (tt.conversion_hrs * tte.tiempo) from tiempo tt"+
						"	join tareas_ejecuciones tte on tt.id = tte.tiempo"+
						"	where tte.id = te.id"+
						") as hrsEstimadas,"+
						"("+
						"	Select ((sum(sbt.minutos)/60)+ sum(sbt.hora)) from bitacora_tarea sbt"+
						"	join tareas_ejecuciones ste on ste.id = sbt.id_tarea"+
						"	where ste.id = te.id"+
						") as hrsDedicadas,"+
						"case "+
						"when sysdate() > te.fin_estimado then"+ 
						"	'SI' "+
						"else 'NO' "+
						"end as atrasada, "+
						"case "+
						"when te.estados_ejecucion_id = 2 then "+
						"	(select xte.desviacion from tareas_ejecuciones xte"+
						"		where xte.id = te.id) "+
						"when te.estados_ejecucion_id = 4 then"+
						"	(select xte.desviacion_fin from tareas_ejecuciones xte"+
						"		where xte.id = te.id)"+
						"else 0 "+
						"end as hrsAtraso, "+
						"(select group_concat(zbt.comentarios) from bitacora_tarea zbt "+
							"where zbt.id_tarea = te.id "+
							"group by te.id"+
						") as comentario "+
						"	FROM proyecto_ejecuciones pe"+
						"	JOIN proyecto p ON p.id = pe.Proyecto_id"+
						"	JOIN tareas_ejecuciones te ON te.proyecto_ejecucion_id = pe.id"+
						"	JOIN tareas t ON t.id = te.Tareas_id"+
						"	JOIN usuarios u ON u.id = t.idresponsable"+
						"	JOIN areas a ON a.id = u.Areas_id"+
						"	JOIN estados_ejecucion ee ON ee.id = te.estados_ejecucion_id"+
						"	WHERE ee.id = 2"+
						"	and pe.id = "+idProyecto;
		 System.out.println(sql);
		 
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			LocatorDao.getInstance();
			LocatorDao.getInstance();
			
			while (rs.next()) {
				if(lp == null){
					lp = new ArrayList<ReportesRevisarProyecto>();
				}
				ReportesRevisarProyecto rr = new ReportesRevisarProyecto();
				rr.setNombreProyecto(rs.getString(1));
				rr.setNombreTarea(rs.getString(2));
				rr.setNombreArea(rs.getString(3));
				rr.setNombreResponsable(rs.getString(4));
				rr.setEstado(rs.getString(5));
				rr.setInicioEstimada(sdf.format(rs.getDate(6)));
				rr.setInicioReal(sdf.format(rs.getDate(7)));
				rr.setTerminoEstimada(sdf.format(rs.getDate(8)));
				rr.setHrsEstimadas(rs.getInt(9));
				rr.setHrsDedicadas(rs.getInt(10));
				rr.setAtrasada(rs.getString(11));
				rr.setHrsAtrasado(rs.getInt(12));
				rr.setComentario(rs.getString(13));
				lp.add(rr);
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
}
