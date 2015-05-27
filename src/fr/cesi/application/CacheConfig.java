package fr.cesi.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.cesi.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The class load the config file and offers methods to get the properties.
 */
public class CacheConfig {

	/** The configfile. */
	private final String CONFIGFILE = "conf/config.ini"; 
	
	/** The properties. */
	private Properties properties = new Properties();
	
	/** The instance. */
	private static CacheConfig instance = new CacheConfig();

	/**
	 * Gets the single instance of CacheConfig.
	 *
	 * @return single instance of CacheConfig
	 */
	public static CacheConfig getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new cache config.
	 */
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
	 * @param id the id
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
