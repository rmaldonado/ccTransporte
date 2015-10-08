package CapaDatos;

import CapaEntidades.UsuarioBE;
import CapaUtil.Conexion;
import CapaUtil.Operador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsuarioDAO {
    UsuarioBE Usu = new UsuarioBE(); 
     
    //Connection cone = Conexion.getConexion();
    
//    public List<UsuarioBE> VerificarUsuario(String Usuario, String clave) throws Exception{
//        List<UsuarioBE> Usus = new ArrayList<UsuarioBE>(); 
//        String query = "SELECT idUsuario,usuLogeo,usuContrasenia from usuario WHERE usuLogeo = '" + Usuario + "' AND usuContrasenia='" + clave + "'";
//        List objectos = Operador.ejecutarSentencia(query);
//        
//        for (Object object : objectos){
//        
//            HashMap map = (HashMap) object;
//            
//            Usu.IdUsuario = (int) map.get("idUsuario");        
//            Usu.UsuLogeo = (String) map.get("usuLogeo");      
//            Usu.UsuContrasenia = (String) map.get("usuContrasenia");
//            Usus.add(Usu);
//            }
//        System.out.println(Usus);
//        return Usus;
//        
//    }
    
    public UsuarioBE GetVerificarUsuario(String Usuario, String Clave) throws SQLException {
        UsuarioBE usuarioBE = null;
        String query = "SELECT idUsuario,usuLogeo,usuContrasenia,usuEstado, idPerfil, idEmpleado "
                    + "FROM usuario "
                    + "WHERE usuLogeo = '" + Usuario + "'"
                    + "AND usuContrasenia='" + Clave + "'";
        
        try {
            Connection cone = Conexion.getConexion();
            
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                usuarioBE = new UsuarioBE();
                usuarioBE.IdUsuario = rs.getInt("idUsuario");
                usuarioBE.UsuLogeo = rs.getString("usuLogeo");
                usuarioBE.UsuContrasenia = rs.getString("usuContrasenia");
                usuarioBE.usuEstado = rs.getInt("usuEstado");
                usuarioBE.idPerfil = rs.getInt("idPerfil");
                usuarioBE.idEmpleado = rs.getInt("idEmpleado");
               
                
            }
            
        }catch(SQLException ex) {
            throw new SQLException(ex);
        }
        return usuarioBE;
    }
    public ArrayList<UsuarioBE> GetAllClientes() throws SQLException {
        ArrayList<UsuarioBE> usuariosBE = null;
        String query = "SELECT * from usuario ";
        
        try {
            Connection cone = Conexion.getConexion();
            
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            usuariosBE = new ArrayList<UsuarioBE>();
            while(rs.next()){
                UsuarioBE usuarioBE = new UsuarioBE();
                //usuarioBE = new UsuarioBE();
                usuarioBE.IdUsuario = rs.getInt("idUsuario");
                usuarioBE.UsuLogeo = rs.getString("usuLogeo");
                usuarioBE.UsuContrasenia = rs.getString("usuContrasenia");
                usuariosBE.add(usuarioBE);
            }
            
        }catch(SQLException ex) {
            throw new SQLException(ex);
        }
        return usuariosBE;        
    }

}