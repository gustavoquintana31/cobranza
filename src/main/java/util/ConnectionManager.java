/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cbustamante
 */
public class ConnectionManager {
    
    public static Connection getConnection(){
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/db_cast", "cbustamante", "12345");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
        
        if(connection!=null){
            System.out.println("Connections ready");
            
        }
        else{
            System.out.println("Error connections");
        }
        
        return connection;
    }
    public static void main(String[] args) throws SQLException {
        if (new ConnectionManager().getConnection() != null) 
            System.out.println("CONNECTION OK!");
        else
            System.out.println("ERROR CONNECTION");
       
    }
}
