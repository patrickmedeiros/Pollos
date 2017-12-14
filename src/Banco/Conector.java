/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author gabrielrm
 */
public class Conector {
    private String hostname;
    private int port;
    private String database;
    private String username;
    private String password;
    
    private Connection connection;
    
    public Conector(){
        try{
            hostname = "localhost";
            port = 3306;
            database = "pollos";
            username = "root";
            password = "";
            
            String url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, username, password);
            
            
        }catch(SQLException ex){
            System.err.println("Erro na conexão "+ex.getMessage());
        }catch(Exception ex){
            System.err.println("Erro geral "+ex.getMessage());
        }
        
    }

    public Connection getConnections(){
        return this.connection;
    }
    
    public void clouseDataConnections(){
        try{
            connection.close();
        }catch(Exception ex){
            System.err.println("Erro ao desconectar "+ex.getMessage());
        }
    }

}
