/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class PostgreSQLConnect
{
    Connection connect = null;
    private static final Logger log = Logger.getLogger(PostgreSQLConnect.class.getName());
    
    /**
     *
     * @param data stores information regarding the connection to the DB
     */
    public PostgreSQLConnect(ConnectionData data)
    {
        // Attempt to connect to the database
        try
        {
            Class.forName(data.getType());
            connect = DriverManager.getConnection(data.toString(), data.getLogin(), data.getPassword());
        }
         
        catch(Exception e)
        {
            log.log(Level.SEVERE, "The connection to the database was NOT successful.", e);
        }
         
        log.log(Level.INFO, "The connection to the database was successful.");
         
    }
    
    /**
     *
     * @return string with all connection info to the database
     */
    public Connection getConnection()
    {
        log.log(Level.SEVERE, "Getting connection to the database from SQL class");
        
        return connect;
    }
   
}
