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

import cl.signosti.kantar.consistencia.modelo.Usuariom;

public class UsuariosDao extends JdbcDaoSupport implements Serializable {
	private static final long serialVersionUID = 2591440895912183790L;
	private static final Logger logger = Logger.getLogger(UsuariosDao.class);

	public Usuariom getUsuario(String user, String pass) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Usuariom entidad = new Usuariom();

		String sql = "SELECT * FROM usuarios where email=? and password=?";


		try {
			conn = getDataSource().getConnection();
			
			pre = conn.prepareStatement(sql);
			pre.setString(1, user);
			pre.setString(2, pass);
			rs = pre.executeQuery();

			while (rs.next()) {

				entidad.setId(rs.getInt("id"));
				entidad.setEmai(rs.getString("email"));
				entidad.setPassword("password");
				entidad.setNombre(rs.getString("nombre"));
				entidad.setApellido(rs.getString("apellido"));

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

		return entidad;
	}
	public List<Usuariom> getusers(int cod) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		

		String sql = "SELECT * FROM usuarios where Areas_id=?";
		List<Usuariom> list =new ArrayList<Usuariom>();

		try {
			conn = getDataSource().getConnection();
			
			pre = conn.prepareStatement(sql);
			pre.setInt(1,cod);
			rs = pre.executeQuery();

			while (rs.next()) {
				Usuariom entidad = new Usuariom();
				entidad.setId(rs.getInt("id"));
				entidad.setEmai(rs.getString("email"));
				entidad.setPassword("password");
				entidad.setNombre(rs.getString("nombre"));
				entidad.setApellido(rs.getString("apellido"));
				list.add(entidad);
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

		return list;
	}
	

	
	public Usuariom getUsuario(int idresponsable) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		Usuariom entidad = new Usuariom();

		String sql = "SELECT * FROM usuarios where id=?";


		try {
			conn = getDataSource().getConnection();
			
			pre = conn.prepareStatement(sql);
			pre.setInt(1, idresponsable);
			
			rs = pre.executeQuery();

			while (rs.next()) {

				entidad.setId(rs.getInt("id"));
				entidad.setEmai(rs.getString("email"));
				entidad.setPassword("password");
				entidad.setNombre(rs.getString("nombre"));
				entidad.setApellido(rs.getString("apellido"));

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

		return entidad;
	}
	
		public List<Usuariom> getuser() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		

		String sql = "SELECT * FROM usuarios";
		List<Usuariom> list =new ArrayList<Usuariom>();

		try {
			conn = getDataSource().getConnection();
			
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				Usuariom entidad = new Usuariom();
				entidad.setId(rs.getInt("id"));
				entidad.setEmai(rs.getString("email"));
				entidad.setPassword("password");
				entidad.setNombre(rs.getString("nombre"));
				entidad.setApellido(rs.getString("apellido"));
				entidad.setArea(rs.getString("Areas_id"));
				list.add(entidad);
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

		return list;
	}
}
