/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaEntidades.EmpleadoBE;
import CapaUtil.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class EmpleadoDAO {
    
    EmpleadoBE emp = new EmpleadoBE();
    
    public EmpleadoBE GetEmpleadoByID(int idEmpleado) throws SQLException {
        EmpleadoBE empleadoBE = null;
        String query = "SELECT idEmpleado, empDni, empApepat, empApemat, empNombres "
                    + "FROM empleado "
                    + "WHERE idEmpleado = " + idEmpleado;
        
        try {
            Connection cone = Conexion.getConexion();
            
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                empleadoBE = new EmpleadoBE();
                empleadoBE.idEmpleado = rs.getInt("idEmpleado");
                empleadoBE.empDni = rs.getString("empDni");
                empleadoBE.empApepat = rs.getString("empApepat");
                empleadoBE.empApemat = rs.getString("empApemat");
                empleadoBE.empNombres = rs.getString("empNombres");
            }
            
        }catch(SQLException ex) {
            // System.out.println('Error'+ex.getMessage(););
            throw new SQLException(ex);
        }
        return empleadoBE;
    }
    public ArrayList<EmpleadoBE> GetEmpleadoAll() throws SQLException {
        ArrayList<EmpleadoBE> empleadosBE = null;
        String query = "SELECT * from usuario ";
        
        try {
            Connection cone = Conexion.getConexion();
            
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            empleadosBE = new ArrayList<EmpleadoBE>();
            while(rs.next()){
                EmpleadoBE usuarioBE = new EmpleadoBE();
                //usuarioBE = new UsuarioBE();
                usuarioBE.idEmpleado = rs.getInt("idUsuario");
                usuarioBE.empDni = rs.getString("usuLogeo");
                usuarioBE.empApepat = rs.getString("usuContrasenia");
                usuarioBE.empApemat = rs.getString("usuContrasenia");
                usuarioBE.empNombres = rs.getString("usuContrasenia");
                empleadosBE.add(usuarioBE);
            }
            
        }catch(SQLException ex) {
            throw new SQLException(ex);
        }
        return empleadosBE;        
    }
}
