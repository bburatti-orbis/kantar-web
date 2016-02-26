package cl.signosti.kantar.consistencia.loggers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import cl.signosti.kantar.consistencia.constans.Constans;

public class Log4jLauncher extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * inicializa el servlet.
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			String path = System.getProperty(Constans.PATH_CONF);
			if (path != null) {
				String fileSt = path + Constans.PROPERTIES + Constans.PROPERTIES
						+ Constans.ARCHIVO_LOG4J;
				System.out.println("_________fileSt_____________________");
				System.out.println(fileSt);
				PropertyConfigurator.configure(fileSt);
			} else {
				initOther();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Destruye el servlet.
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * doGet Servlet method
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}

	public void initOther() {

		try {
		
			PropertyConfigurator.configure(     Constans.PATH_CONF+ Constans.PROPERTIES + Constans.ARCHIVO_LOG4J);

		} catch (Exception e) {
			e.getStackTrace();
		} finally {

		}
	}
}