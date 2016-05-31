package cl.signosti.kantar.consistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cl.signosti.kantar.consistencia.modelo.Conjuntom;
import cl.signosti.kantar.consistencia.utils.Close;


public class ConjuntoDao  extends JdbcDaoSupport {
	
	private static final Logger logger = Logger.getLogger(ConjuntoDao.class);
	public int setbase(String b) {

		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		String sql = "INSERT INTO Conjunto(nombre) "
				+ "values (?)";

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pre.setString(1, b);

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
	public List<Conjuntom> getconjunto(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		List<Conjuntom> base = new ArrayList<Conjuntom>();
		String sql = "select DISTINCT   a.id,a.nombre from conjunto a, bases b,paises c, proyecto d where a.id=b.Conjunto_id and b.Paises_id=c.id  and d.Paises_id=c.id and d.id=?";
		 System.out.println(sql);

		try {
			conn = getDataSource().getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, cod);
			rs = pre.executeQuery();
			
			while (rs.next()) {
				Conjuntom c=new Conjuntom();
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
				base.add(c);

			}

		} catch (Exception e) {
			 logger.error("Error, causa:" , e);
		} finally {
			try {
				Close.all(rs, pre, conn);
			} catch (SQLException e) {

			}

		}

		return base;
	}
}
