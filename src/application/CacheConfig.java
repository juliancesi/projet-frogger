package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import util.Utils;

public class CacheConfig {

	private final String CONFIGFILE = "config.ini"; 

	Properties properties = new Properties();
	
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
	
	public String getProperty(String property) {
		return properties.getProperty(property);
	}
	
	public Integer getRoundDuration() {
		return Utils.tryParseToInt(getProperty("roundDuration"));
	}

	public Integer getLifesNumber() {
		return Utils.tryParseToInt(getProperty("lifes"));
	}

}
