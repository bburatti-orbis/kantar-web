package cl.signosti.kantar.consistencia.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.constans.Constans;

public class PropertiesUtil {

	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private Map<String, String> map;
	private static PropertiesUtil instance;

	private PropertiesUtil() {
		initWas();
	}

	public static PropertiesUtil getInstance() {
		if (instance == null)
			instance = new PropertiesUtil();
		return instance;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public String recuperaValor(String key) {
		String valor = "";
		try {
			valor = (String) map.get(key);
			valor = valor.trim();
		} catch (Exception e) {
			valor = "";
		}
		return valor;
	}

	public int recuperaValorInt(String key) {
		int valor = -1;
		try {
			valor = Integer.parseInt(recuperaValor(key));
		} catch (Exception e) {
			valor = -1;
		}
		return valor;
	}

	public short recuperaValorShort(String key) {
		return (short) recuperaValorInt(key);
	}

	public long recuperaValorLong(String key) {
		return (long) recuperaValorInt(key);
	}

	@SuppressWarnings("rawtypes")
	public void initWas() {
		String path = Constans.PATH_CONF;

		String fileSt = path + Constans.PROPERTIES + Constans.CONFIG_NAME;
		logger.info("________init was_____________");
		logger.info(fileSt);
		FileInputStream fileInput = null;
		try {
			map = new HashMap<String, String>();
			logger.info("Iniciando Lectura de Archivo de Configuracion "
					+ fileSt);
			fileInput = new FileInputStream(fileSt);
			Properties properties = new Properties();
			properties.load(fileInput);
			Iterator ite = properties.keySet().iterator();
			while (ite.hasNext()) {
				String key = (String) ite.next();
				String value = (String) properties.get(key);
				logger.info("_key:" + key + " value:" + value);
				map.put(key, value);
			}
		} catch (Exception e) {
			logger.error("Error Configuracion Lectura de Archivo de Configuracion ["
					+ fileSt + "].");
			e.printStackTrace();

		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
