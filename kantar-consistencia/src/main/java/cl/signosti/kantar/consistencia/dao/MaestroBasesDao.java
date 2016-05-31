package cl.signosti.kantar.consistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.MaestroBase;
import cl.signosti.kantar.consistencia.utils.Close;

public class MaestroBasesDao extends JdbcDaoSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MaestroBasesDao.class);

	public MaestroBase getMaestroBase(int id) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		MaestroBase maestroBase = new MaestroBase();

		String sql = "SELECT * FROM MestroBases WHERE id=?";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();

			while (rs.next()) {
				maestroBase.setId(rs.getInt("id"));
				maestroBase.setGlosa(rs.getString("glosa"));
				maestroBase.setCategoria(rs.getString("categoria"));
				maestroBase.setIdcliente(rs.getInt("idCliente"));
				maestroBase.setIdencargado(rs.getInt("idEncargado"));
				maestroBase.setIdejecutivo(rs.getInt("idEjecutivo"));
				maestroBase.setIdpais(rs.getInt("idPais"));
			}

		} catch (Exception e) {
			logger.error("Error, causa:", e);
		} finally {
			try{
				Close.all(rs, pre, conn);
			} catch (Exception e){
				// no importan por ahora
			}
		}
		return maestroBase;
	}
}
