/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class ConnectionData
{
    String type;
    String url;
    String ipaddress;
    String port;
    String database;
    String login;
    String password;
    
    private static final Logger log = Logger.getLogger(ConnectionData.class.getName());
    
    /**
     *
     * @return string of the connection to the database
     */
    // Creating string with connection data from database connection
    public String toString()
    {
        String connection = new String();
        
        connection = url + "://" + ipaddress + ":" + port + "/" + database;
        
        log.log(Level.SEVERE, "Getting information on database connection");
        
        return connection;
    }

    /**
     *
     * @return string of the type of connection
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type string of the connection type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return string of the url of the database
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url string of the url to the database
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return string of the database's ip address
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     *
     * @param ipaddress string of the ip address
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     *
     * @return string holding the port being used to communicate with the DB
     */
    public String getPort() {
        return port;
    }

    /**
     *
     * @param port string holding the port being used to communicate with the DB
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     *
     * @return string holding name of the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     *
     * @param database string holding name of the database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     *
     * @return string holding the login name to the database
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login string holding the login name to the database
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return string holding the password to connect to the database
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password string holding the password to connect to the database
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
