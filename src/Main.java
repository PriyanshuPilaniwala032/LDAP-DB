import utils.ConfigLoader;
import utils.LDAPUtils;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.HashMap;
import java.util.Hashtable;

public class Main {
    public static boolean authetication(String ldapUrl, String userDn, String pasword){
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, userDn);
        env.put(Context.SECURITY_CREDENTIALS, pasword);

        DirContext context = null;
        try{
            context = new InitialDirContext(env);
            return true;

        }catch (NamingException e){
            System.err.println("Autentication Failed: " + e.getMessage());
            return false;
        }finally{
            if(context != null){
                try{
                    context.close();
                }catch (NamingException e){
                    System.err.println("Error closing context: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {

        String url = "ldap://localhost:10389";
        String user = "uid=admin,ou=system";
        String pwd = "secret";

        if(authetication(url, user, pwd)){
            System.out.println("User authenticated Successfully");
        }else{
            System.out.println("User authentication failed");
        }
    }


}
