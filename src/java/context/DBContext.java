/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hoand
 */
public class DBContext {
    
    
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber+";databaseName="+dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return DriverManager.getConnection(url, username, password);
    }
    
    private final String serverName ="DESKTOP-9VKKGMQ";
    private final String dbName = "PSneaker";
    private final String username = "sa";
    private final String password = "admin";
    private final String portNumber = "1433";
    
}


