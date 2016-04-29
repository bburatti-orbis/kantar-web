package cl.signosti.kantar.consistencia.utils;

import java.sql.SQLException;
import java.util.Properties;

import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EnvioMail {
	private static final Logger logger = Logger.getLogger(EnvioMail.class);
	public void envio(int cod, int ejec, int proceso, String email)
			throws SQLException {
		LocatorDao.getInstance();
		BasesDao bases = LocatorDao.getBasesDao();
		Basesm base = bases.getBase(cod);

		try {
			String ruta_informes = PropertiesUtil.getInstance().recuperaValor(
					"ruta_informes");

			String emailBody = "La base de datos: "
					+ base.getGlosa().toUpperCase()
					+ " a terminado </br>"
					+ " Para descargar el informe ingrese en el siguiente link </br>"
					+ " <a href='" + ruta_informes + "?cod_proceso=" + proceso
					+ "&&cod_ejec=" + ejec + "' > haz click aqui</a></br> "
					+ "";

			send(email, emailBody);
			
		} catch (Exception e) {
			 logger.error("Error, causa:" ,e);
					
		}

	}

	public String send(String email, String emailBody) {
		LocatorDao.getInstance();
		Properties mailServerProperties;

		Session getMailSession;
		MimeMessage generateMailMessage;

		try {
			String puerto = PropertiesUtil.getInstance().recuperaValor(
					"Puerto_SMTP");
			String correo = PropertiesUtil.getInstance()
					.recuperaValor("Correo");
			String pass = PropertiesUtil.getInstance()
					.recuperaValor("Password");
			String servidor_smtp = PropertiesUtil.getInstance().recuperaValor(
					"Servidor_SMTP");
			// Step1

			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", puerto);
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			mailServerProperties.put("mail.smtp.ssl.enable", "true");
			

			// Step2

			getMailSession = Session.getDefaultInstance(mailServerProperties,
					null);
			getMailSession.setDebug(true);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.setFrom(new InternetAddress(correo));
			generateMailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(email));

			generateMailMessage.setText(emailBody, "UTF-8", "html");

			// Step3

			Transport transport = getMailSession.getTransport("smtp");

			// Enter your correct gmail UserID and Password (XXXApp
			// Shah@gmail.com)
			transport.connect(servidor_smtp, correo, pass);
			transport.sendMessage(generateMailMessage,
					generateMailMessage.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			 logger.error("Error, causa:" ,e);
			 return e.getMessage();
		} 
		
		return null;
	}
}
