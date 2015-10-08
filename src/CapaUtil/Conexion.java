package CapaUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {
    
    private String NombreBD = "musica.mdb";
    private String ConexionBD = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + this.NombreBD;
    
    public static void main (String[] args){
        getConexion();
    }
    
    public static Connection getConexion(){
        Connection cone = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //String url ="jdbc:sqlserver://LAPTOP; databasename = bd_epysa_peru;";
            String url = "jdbc:mysql://localhost:3306/bdtransporte";
            String user = "root";
            String pass = "Password123";
            cone = DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver desconocido... "+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexion..."+e.getMessage());
        }
        return cone;
    }
    
    public static Connection getConeAccess(){
        Connection cone = null;
         
        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  
            //cone = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=BdTransporte.accdb");
            cone = DriverManager.getConnection("jdbc:Ucanaccess//BdTransporte.accdb; memory = false");
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver desconocido... "+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexion..."+e.getMessage());
        }
        return cone;
    }
    
}
