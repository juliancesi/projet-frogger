package fr.cesi.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import fr.cesi.util.Utils;

/**
 * The class load the config file and offers methods to get the properties
 */
public class CacheConfig {

	private final String CONFIGFILE = "config.ini"; 
	private Properties properties = new Properties();
	private static CacheConfig instance = new CacheConfig();

	public static CacheConfig getInstance() {
		return instance;
	}

	private CacheConfig() {
		try(BufferedReader bis = Files.newBufferedReader(Paths.get(CONFIGFILE))) {
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
	 * Get the duration of a round.
	 *
	 * @return  the property
	 */
	public Integer getRoundDuration() {
		return Utils.tryParseToInt(getProperty("roundDuration"));
	}

	/**
	 * Get the number of lifes.
	 *
	 * @return  the property
	 */
	public Integer getLifesNumber() {
		return Utils.tryParseToInt(getProperty("lifes"));
	}

}
