package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
    
    private Connection con;
    
    
    public void setCon(Connection con){
        this.con = con;
    }
    
    public Connection getCon(){
        return this.con;
    }
    
    /**
    * Cria um statement
    * @throws SQLException
    */
    public Statement createStatement() throws SQLException{
        try{
            return this.con.createStatement();
        }
        catch(SQLException ex){
            throw ex;
        }
    }
    /**
    * Conecta à base de dados
    * @param username
    * @param password
    * @return Connection
    */
    public static Connection conectaBD (String username, String password){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(Exception ex){
            System.out.println("Classe "+ex);
        }
        
        try{
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl", username, password);
            return con;
        }
        catch(Exception ex){
            System.out.println("Conecta "+ex);
            return null;
        }
    }
    
    
    /**
    * Disconecta da base
    * @param con
    * @throws SQLException
    */
    public void desconectaBD() throws SQLException{
        this.con.close();
    }
    
    /**
    * Efetua commit na base
    * @throws SQLException
    */
    public void commit() throws SQLException{
        this.con.commit();
    }
    
    /**
    * Efetua rollback na base
    * @throws SQLException
    */
    public void rollback() throws SQLException{
        this.con.rollback();
    }
    
    
}