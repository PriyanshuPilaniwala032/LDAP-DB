package utils;

import java.util.HashMap;

public class LDAPUtils {
    private static HashMap<String, String> config;

    /*
     *Constructor to set LDAP Config, Username and Password
     */
    public LDAPUtils(HashMap<String, String> config){
        LDAPUtils.config = config;
    }
}
