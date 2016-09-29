package cl.signosti.kantar.consistencia.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Close {

	public static void all(Object...listObject) throws SQLException{
		
		for(Object obj: listObject){
			if (obj != null){
				if (obj instanceof ResultSet){
					((ResultSet) obj).close();
				} else if (obj instanceof PreparedStatement){
					((PreparedStatement) obj).close();
				} else if (obj instanceof Connection){
					((Connection) obj).close();
				}
			}
		}
		
	}
}
