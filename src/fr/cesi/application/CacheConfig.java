package fr.cesi.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.cesi.util.Utils;

/**
 * The class load the config file and offers methods to get the properties
 */
public class CacheConfig {

	private final String CONFIGFILE = "conf/config.ini"; 
	private Properties properties = new Properties();
	private static CacheConfig instance = new CacheConfig();

	public static CacheConfig getInstance() {
		return instance;
	}

	private CacheConfig() {
		ClassLoader classLoader = getClass().getClassLoader();
		try(InputStream bis = classLoader.getResourceAsStream(CONFIGFILE)) {
			properties.load(bis);
		} catch(IOException ioE) {
			ioE.printStackTrace();
		}
	}

	/**
	 * Get a property by her name.
	 *
	 * @param property the name
	 * @return the property
	 */
	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	/**
	 * Gets the difficulty.
	 *
	 * @return the difficulty
	 */
	public Integer getDifficulty() {
		return Utils.tryParseToInt(getProperty("difficulty"));
	}

	/**
	 * Get the duration of a round.
	 *
	 * @return  the property
	 */
	public Integer getRoundDuration(int id) {
		return Utils.tryParseToInt(getProperty("roundDuration" + id));
	}

	/**
	 * Get the number of lifes.
	 *
	 * @return  the property
	 */
	public Integer getLifesNumber() {
		return Utils.tryParseToInt(getProperty("lifes"));
	}
	
	/**
	 * Gets the speed.
	 *
	 * @param id the difficulty
	 * @return the speed
	 */
	public Double getSpeed(int id) {
		return Utils.tryParseToDouble(getProperty("speed" + id));
	}

}
