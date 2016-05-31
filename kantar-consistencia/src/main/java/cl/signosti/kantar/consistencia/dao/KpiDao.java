package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.AlcancesKpiM;
import cl.signosti.kantar.consistencia.modelo.Area;
import cl.signosti.kantar.consistencia.modelo.CategoriaKpiM;
import cl.signosti.kantar.consistencia.modelo.ConexionesM;
import cl.signosti.kantar.consistencia.modelo.GrillaSemaforo;
import cl.signosti.kantar.consistencia.modelo.KpiM;
import cl.signosti.kantar.consistencia.modelo.ListadoKpiM;
import cl.signosti.kantar.consistencia.modelo.OperadorSemaforo;
import cl.signosti.kantar.consistencia.modelo.Paisesm;
import cl.signosti.kantar.consistencia.modelo.ParametrosKpi;
import cl.signosti.kantar.consistencia.modelo.ParametrosKpiM;
import cl.signosti.kantar.consistencia.modelo.Semaforo;
import cl.signosti.kantar.consistencia.modelo.SemaforoKpi;
import cl.signosti.kantar.consistencia.modelo.StoreProcedure;
import cl.signosti.kantar.consistencia.utils.Close;

public class KpiDao extends JdbcDaoSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MonitorDao.class);
	
	public List<ListadoKpiM> getListadoKpi() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ListadoKpiM> datos = null;
	
		String sql = "select k.glosa,"+ 
					"	case k.InternoExterno"+
					"	when 0 then 'Interno'"+
					"	when 1 then 'Externo'"+
					"	end as tipo,"+
					"	case k.InternoExterno"+
					"	when 0 then 'Todos'"+
					"	else"+
					"	p.nombre"+
					"	end as pais,"+
					"	akpi.glosa,"+
					"	ak.glosa,"+
					"	ck.glosa,"+
					"	k.id"+
					"	from kpis k"+
					"	join paises p on k.Paises_id = p.id"+
					"	join alcanceskpis akpi on akpi.id = k.AlcancesKPIs_id"+
					"	join areaskpis ak on ak.id = k.AreasKPIs_id"+
					"	join categoriaskpis ck on ck.id = k.CategoriasKPIs_id";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<ListadoKpiM>();
				}
				ListadoKpiM lt = new ListadoKpiM();
				lt.setNombreKpi(rs.getString(1));
				lt.setTipoKpi(rs.getString(2));
				lt.setPais(rs.getString(3));
				lt.setAlcance(rs.getString(4));
				lt.setArea(rs.getString(5));
				lt.setCategoria(rs.getString(6));
				lt.setId(rs.getInt(7));
				datos.add(lt);
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
	
		return datos;
	}

	public ConexionesM getDatosConexion() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		ConexionesM datos = null;

		String sql = "select proyecto_id from proyecto_ejecuciones where estados_ejecucion_id = 2 order by id";
//		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			LocatorDao.getInstance();
			LocatorDao.getInstance();

			while (rs.next()) {
				datos = new ConexionesM();
				datos.setPais(rs.getString(1));
				datos.setIp(rs.getString(2));
				datos.setPuerto(rs.getString(3));
				datos.setNombreDb(rs.getString(4));
				datos.setUser(rs.getString(5));
				datos.setPass(rs.getString(6));
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

		return datos;
	}
	
	public List<Paisesm> getPaises() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Paisesm> datos = null;
	
		//cambiar query para que traiga los paises
		String sql = "select id, nombre from paises";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<Paisesm>();
				}
				Paisesm lt = new Paisesm();
				lt.setId(rs.getInt(1));
				lt.setNombre(rs.getString(2));
				datos.add(lt);
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
	
		return datos;
	}
	
	public List<AlcancesKpiM> getAlcance() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<AlcancesKpiM> datos = null;
	
		//cambiar query para que traiga los paises
		String sql = "select id, glosa from alcanceskpis";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<AlcancesKpiM>();
				}
				AlcancesKpiM lt = new AlcancesKpiM();
				lt.setId(rs.getInt(1));
				lt.setNombre(rs.getString(2));
				datos.add(lt);
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
	
		return datos;
	}
	
	public List<Area> getAreas() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Area> datos = null;
	
		//cambiar query para que traiga los paises
		String sql = "select id, glosa from areaskpis";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<Area>();
				}
				Area lt = new Area();
				lt.setIdArea(rs.getInt(1));
				lt.setDesc(rs.getString(2));
				datos.add(lt);
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
	
		return datos;
	}
	
	public List<CategoriaKpiM> getCategorias () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<CategoriaKpiM> datos = null;
	
		String sql = "select id, glosa from categoriaskpis";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<CategoriaKpiM>();
				}
				CategoriaKpiM lt = new CategoriaKpiM();
				lt.setId(rs.getInt(1));
				lt.setNombre(rs.getString(2));
				datos.add(lt);
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
	
		return datos;
	}
	
	public List<ParametrosKpi> getParamDefecto () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ParametrosKpi> datos = null;
	
		String sql = "select kp.id, p.nombre, kp.defaultI, kp.defaultT, kp.defaultD, kp.defaultS, kp.order "+
						"from kpiparameters kp "+
						"join parametros p on p.id = kp.Parametros_id "+
						"where kp.order in (1,2) order by kp.order";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<ParametrosKpi>();
				}
				ParametrosKpi lt = new ParametrosKpi();
				lt.setIdP(rs.getInt(1));
				lt.setNombreP(rs.getString(2));
				lt.setDefaultI(rs.getInt(3));
				lt.setDefaultT(rs.getDate(4));
				lt.setDefaultD(rs.getDouble(5));
				lt.setDefaultS(rs.getString(6));
				lt.setOrden(rs.getInt(7));
				datos.add(lt);
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
		return datos;
	}
	
	public List<ParametrosKpi> getCargaParam () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ParametrosKpi> datos = null;
	
		String sql = "select p.id, p.nombre , t.tipo "+ 
					 "from parametros p "+
					 "join tiposdatos t on t.id = p.TipoDatos "+
					 "join kpiparameters kp on p.id = kp.Parametros_id "+
					 "where kp.order not in (1,2) group by p.id;";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<ParametrosKpi>();
				}
				ParametrosKpi lt = new ParametrosKpi();
				lt.setIdP(rs.getInt(1));
				lt.setNombreP(rs.getString(2));
				lt.setTipoDato(rs.getString(3));
//				System.out.println("tipo dato: "+lt.getDefaultS());
				datos.add(lt);
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
		return datos;
	}
	
	public List<StoreProcedure> getSpInterno () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<StoreProcedure> datos = null;
	
		String sql = "select id, nombre from sp_interno";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<StoreProcedure>();
				}
				StoreProcedure lt = new StoreProcedure();
				lt.setId(rs.getInt(1));
				lt.setNombre(rs.getString(2));
				datos.add(lt);
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
		return datos;
	}
	
	public List<StoreProcedure> getSpExterno () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<StoreProcedure> datos = null;
	
		String sql = "select id, nombre from sp_externo";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<StoreProcedure>();
				}
				StoreProcedure lt = new StoreProcedure();
				lt.setId(rs.getInt(1));
				lt.setNombre(rs.getString(2));
				datos.add(lt);
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
		return datos;
	}
	
	public List<Semaforo> getColores () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<Semaforo> datos = null;
	
		String sql = "select id, color from semaforos";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()){
				if(datos == null){
					datos = new ArrayList<Semaforo>();
				}
				Semaforo lt = new Semaforo();
				lt.setId(rs.getInt(1));
				lt.setColor(rs.getString(2));
				datos.add(lt);
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
		return datos;
	}
	
	public List<OperadorSemaforo> getOperadores () throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<OperadorSemaforo> datos = null;
	
		String sql = "select id, operador from opers";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<OperadorSemaforo>();
				}
				OperadorSemaforo lt = new OperadorSemaforo();
				lt.setId(rs.getInt(1));
				lt.setOperador(rs.getString(2));
				datos.add(lt);
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
		return datos;
	}
	
	public int grabaKpi(KpiM kpi) throws SQLException {
//		System.out.println("[grabaKpi]   metodo con el campo paises");
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "INSERT INTO `kpis` (`glosa`, `InternoExterno`, `nombreSP`, `valorTeoricoD`,"+
										 "`Paises_id`, `EstadosKPIs_id`, `CategoriasKPIs_id`, `AlcancesKPIs_id`, `AreasKPIs_id`, "+
										 "`created_at`, `updated_at`) "+
					 "VALUES (?,?,?,?,?,?,?,?,?,sysdate(),sysdate())";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, kpi.getGlosa());
			pre.setString(2, kpi.getTipo());
			pre.setString(3, kpi.getNombreSp());
			pre.setDouble(4, kpi.getValorTeoricoD());
			pre.setInt(5, kpi.getPais());
			pre.setInt(6, 4);
//			pre.setInt(6, kpi.getEstado());
			pre.setInt(7, kpi.getCategoria());
			pre.setInt(8, kpi.getAlcance());
			pre.setInt(9, kpi.getArea());

			pre.executeUpdate();

			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return id;
	}
	
	public int grabaKpiSinPais(KpiM kpi) throws SQLException {
//		System.out.println("[grabaKpi2]   metodo sin el campo paises");
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "INSERT INTO `kpis` (`glosa`, `InternoExterno`, `nombreSP`, `valorTeoricoD`,"+
										 "`EstadosKPIs_id`, `CategoriasKPIs_id`, `AlcancesKPIs_id`, `AreasKPIs_id`, "+
										 "`created_at`, `updated_at`) "+
					 "VALUES (?,?,?,?,?,?,?,?,sysdate(),sysdate())";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, kpi.getGlosa());
			pre.setString(2, kpi.getTipo());
			pre.setString(3, kpi.getNombreSp());
			pre.setDouble(4, kpi.getValorTeoricoD());
			pre.setInt(5, 4);
//			pre.setInt(6, kpi.getEstado());
			pre.setInt(6, kpi.getCategoria());
			pre.setInt(7, kpi.getAlcance());
			pre.setInt(8, kpi.getArea());

			pre.executeUpdate();

			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return id;
	}
	
	public int grabarParametros(ParametrosKpiM param) {
		String sql = "INSERT INTO `kpiparameters` (`description`, `Kpis_id`, `Parametros_id`, `sizeTipo`, `order`,"+ 
												  "`defaultS`, `defaultD`, `defaultI`, `created_at`, `updated_at`)"+
					 " VALUES (?,?,?,?,?,?,?,?,sysdate(),sysdate())";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int id = 0;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, param.getDescripcion());
			pre.setInt(2, param.getKpi());
			pre.setInt(3, param.getParametro());
			pre.setInt(4, 0);
			pre.setInt(5, param.getOrden());
			pre.setString(6, param.getDefectoS());
			if(param.getDefectoD()==0){
				pre.setNull(7, Types.DOUBLE);
			}else{
				pre.setDouble(7, param.getDefectoD());
			}
			
			if(param.getDefectoI()==0){
				pre.setNull(8, Types.INTEGER);
			}else{
				pre.setInt(8, param.getDefectoI());
			}
			
			pre.executeUpdate();

			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return id;
	}
	
	public int grabarSemaforos(SemaforoKpi sema) {
		String sql = "INSERT INTO `kpisemaforos` (`Paises_id`, `KPIs_id`, `order`, `Opers_id`, `value`, `Semaforos_id`, `created_at`, `updated_at`)"+
					 " VALUES (?,?,?,?,?,?,sysdate(),sysdate())";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int id = 0;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, sema.getPais());
			pre.setInt(2, sema.getKpi());
			pre.setInt(3, sema.getOrden());
			pre.setInt(4, sema.getOperador());
			pre.setDouble(5, sema.getValor());
			pre.setInt(6, sema.getColor());
			
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
	
	public int grabarSemaforosSinPais(SemaforoKpi sema) {
		String sql = "INSERT INTO `kpisemaforos` (`KPIs_id`, `order`, `Opers_id`, `value`, `Semaforos_id`, `created_at`, `updated_at`)"+
					 " VALUES (?,?,?,?,?,sysdate(),sysdate())";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		int id = 0;
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setInt(1, sema.getKpi());
			pre.setInt(2, sema.getOrden());
			pre.setInt(3, sema.getOperador());
			pre.setDouble(4, sema.getValor());
			pre.setInt(5, sema.getColor());
			
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
	
	public KpiM getKpi (int id) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		KpiM datos = new KpiM();
//		System.out.println("edita kpi.. dao");
		String sql = "select k.id, k.glosa, k.InternoExterno, k.Paises_id, k.nombreSP, "+
						"k.AlcancesKPIs_id, k.AreasKPIs_id, k.CategoriasKPIs_id "+
						"from kpis k where k.id ="+id;
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
	
			while (rs.next()) {
				datos.setId(rs.getInt(1));
				datos.setGlosa(rs.getString(2));
				datos.setTipo(rs.getString(3));
				datos.setPais(rs.getInt(4));
				datos.setNombreSp(rs.getString(5));
				datos.setAlcance(rs.getInt(6));
				datos.setArea(rs.getInt(7));
				datos.setCategoria(rs.getInt(8));
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
//		System.out.println("resultados query "+datos.getGlosa());
		return datos;
	}
	
	public List<ParametrosKpi> getParam (int id) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<ParametrosKpi> datos = null;
	
		String sql = "select k.id, k.description, k.`order`, k.defaultS, k.defaultD, k.defaultI, k.Parametros_id, t.tipo "+
						"from kpiparameters k  "+
						"join parametros p on k.Parametros_id = p.id "+
						"join tiposdatos t on t.id = p.TipoDatos "+
						"where k.kpis_id = "+id+" order by 'order'";
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<ParametrosKpi>();
				}
				ParametrosKpi lt = new ParametrosKpi();
				lt.setIdP(rs.getInt(1));
				lt.setNombreP(rs.getString(2));
				lt.setOrden(rs.getInt(3));
				lt.setDefaultS(rs.getString(4));
				lt.setDefaultD(rs.getDouble(5));
//				System.out.println("rs.getInt(6) "+rs.getInt(6));
				lt.setDefaultI(rs.getInt(6));
				lt.setParamId(rs.getInt(7));
				lt.setTipoDato(rs.getString(8));
				
				datos.add(lt);
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
		return datos;
	}
	
	public List<GrillaSemaforo> getUmbrales (int id) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<GrillaSemaforo> datos = null;
	
		String sql = "select k.id, s.id, s.color, o.id, o.operador, k.value "+
						"from kpisemaforos k "+
						"join semaforos s on s.id = k.Semaforos_id "+
						"join opers o on o.id = k.Opers_id "+
						"where k.KPIs_id = "+id;
//		 System.out.println(sql);
	
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
	
			LocatorDao.getInstance();
			LocatorDao.getInstance();
	
			while (rs.next()) {
				if(datos == null){
					datos = new ArrayList<GrillaSemaforo>();
				}
				GrillaSemaforo lt = new GrillaSemaforo();
				lt.setIdRango(rs.getInt(1));
				lt.setIdUmbral(rs.getInt(2));
				lt.setColor(rs.getString(3));
				lt.setIdOper(rs.getInt(4));
				lt.setOper(rs.getString(5));
				lt.setValor(rs.getInt(6));
				datos.add(lt);
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
		return datos;
	}
	
	public boolean actualizaKpi(KpiM kpi) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		String sql = "UPDATE kpis SET glosa=?, InternoExterno=?, nombreSp =?, valorTeoricoD=?,  Paises_id=?, CategoriasKPIs_id=?, "+
					 "AlcancesKPIs_id=?, AreasKPIs_id=?, updated_at= CURRENT_TIMESTAMP()"+
					 "WHERE id=?";
//		System.out.println("[actualizaKpi] ----> "+sql);
		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, kpi.getGlosa());
			pre.setString(2, kpi.getTipo());
			pre.setString(3, kpi.getNombreSp());
			pre.setDouble(4, 0);
			if(kpi.getTipo().equals(0)){
				pre.setNull(5, java.sql.Types.INTEGER);
//				System.out.println("Seteando nulo");
			}else{
//				System.out.println("Seteando el pais");
				pre.setInt(5, kpi.getPais());
			}
			pre.setInt(6, kpi.getCategoria());
			pre.setInt(7, kpi.getAlcance());
			pre.setInt(8, kpi.getArea());
			pre.setInt(9, kpi.getId());
			pre.executeUpdate();
			flag = true;
		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
			 e.printStackTrace();
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}
		}
		return flag;
	}
}
