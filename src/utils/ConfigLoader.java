package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Properties;

public class ConfigLoader {
    private final String configPath = "config.properties";
    private static ConfigLoader instance;

    HashMap<String, String> ldap_config = new HashMap<>();

    private ConfigLoader(){
        try{
            Properties configLoad = new Properties();
            FileInputStream configFile = new FileInputStream(this.configPath);
            configLoad.load(configFile);

            ldap_config.put("ldap_url", configLoad.getProperty("ldap.url"));
            ldap_config.put("ldap_auth", configLoad.getProperty("ldap.auth"));
            ldap_config.put("ldap_driver", configLoad.getProperty("ldap.driver"));
        } catch (Exception e) {
            System.err.println("Got error in loading the propertes: " + e.getMessage());
        }
    }

    /*
    * Follows singleton pattern
    *
    * @returns the same instance of the configurator
    */
    public static ConfigLoader getInstance() {
        if (instance == null) {
            return new ConfigLoader();
        }
        return instance;
    }

    /*
     * APACHE DS config
     *
     * @returns ldap confi
     */
    public HashMap<String, String> getLdap_config(){
        return ldap_config;
    }
}