/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Operador {
          
    public Object getEntidadQuery(String query, String nomClase) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Class clase = Class.forName(nomClase);
        Field props[] = clase.getFields();
        Object entidad = clase.newInstance();    
        Connection cone = Conexion.getConexion();
        try {
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rmd = rs.getMetaData();
            List<String> columnas = new ArrayList();
            for(int j=1; j<= rmd.getColumnCount(); j++){
                columnas.add(rmd.getColumnName(j));
            }
            while(rs.next()){
                for(Field propiedad:props){ 
                    String nameType = propiedad.getType().getName();
                    String nameCol = propiedad.getName();
                    // System.out.println("columna :"+nameCol+" / tipo de dato: "+nameType);
                    
                    // System.out.println("Dato :"+rs.getInt(nameCol));
                    
                    if(columnas.contains(nameCol)) {
                        switch (nameType) {
                            case "double":
                                propiedad.setDouble(entidad, rs.getDouble(nameCol));
                                break;
                            case "int":
                                propiedad.setInt(entidad, rs.getInt(nameCol));
                                break;
                            case "byte":
                                propiedad.setByte(entidad, rs.getByte(nameCol));
                                break;
                            default:
                                propiedad.set(entidad, rs.getObject(nameCol));
                                break;
                        }
                    }                    
                }            
            }
            st.close();
            rs.close();
            cone.close();            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            if(!cone.isClosed()) cone.close();
        } 
        return entidad;
    }
    
    public List<Object> getEntidadesQuery(String query, String nomClase) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        List<Object> lista = new ArrayList();
        Class clase = Class.forName(nomClase);
        Field props[] = clase.getFields();          
        Connection cone = Conexion.getConexion();  
        try {
//            PreparedStatement pst = cone.prepareStatement(query);
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rmd = rs.getMetaData();
            List<String> columnas = new ArrayList();
            for(int j=1; j<= rmd.getColumnCount(); j++){
                columnas.add(rmd.getColumnName(j));
            }
            while(rs.next()){
                Object entidad = clase.newInstance();  
                for(Field propiedad:props){ 
                    String nameType = propiedad.getType().getName();
                    String nameCol = propiedad.getName();
                    System.out.println("columna :"+nameCol+" / tipo de dato: "+nameType);
                    if(columnas.contains(nameCol)) {
                        switch (nameType) {
                            case "double":
                                propiedad.setDouble(entidad, rs.getDouble(nameCol));
                                break;
                            case "int":
                                propiedad.setInt(entidad, rs.getInt(nameCol));
                                break;
                            case "byte":
                                propiedad.setByte(entidad, rs.getByte(nameCol));
                                break;
                            default:
                                propiedad.set(entidad, rs.getObject(nameCol));
                                break;
                        }
                    }                    
                }
                lista.add(entidad);
            }
            st.close();
            rs.close();
            cone.close();            
        } catch (SQLException e) {
            System.out.println("error al procesar consulta entidades... "+e.getMessage());
        } finally{
            if(!cone.isClosed()) cone.close();
        } 
        return lista;
    }
    
    public Object getEscalarSql(String query) throws SQLException{
        Connection cone = Conexion.getConexion();
        Object resu = null;
        try{            
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){               
                resu = rs.getObject(1);
            }
            st.close();
            rs.close();
            cone.close();
        }finally{
            if(!cone.isClosed()) cone.close();
        }        
        return resu;        
    }
    
    public List<Object> getEscalaresProcedimiento(String nom_sp,List<Object> param_entrada,List<Object> param_salida) throws SQLException{
        Connection cone = Conexion.getConexion();
        String query = "{call "+nom_sp+"(";
        int tot = param_entrada.size()+param_salida.size();
        for(int i=0; i< tot; i++){
            query = query + "?,";
        }
        query = query.substring(0, query.length()-1);
        query = query+")}";
        
//        System.out.println(query);
        
        List<Object> resultado = new ArrayList();
        
        try {
            CallableStatement cst = cone.prepareCall(query);
            int j=1;
            for(Object x:param_entrada){
//                System.out.println("Nombre de la clase: "+x.getClass().getName());
                switch(x.getClass().getName()){
                    case "java.lang.String":
                        cst.setString(j, (String)x);
                        break;
                    case "java.lang.Integer":
                        cst.setInt(j, (int)x);
                        break;
                    case "java.lang.Double":
                        cst.setDouble(j, (double)x);
                        break;
                    case "float":
                        cst.setFloat(j, (float)x);
                    default:
                        cst.setString(j, (String)x);
                        break;
                }
                j++;
            }
            
            int k = j; 
            for(Object y:param_salida){
                switch(y.getClass().getName()){
                    case "java.lang.String":
                        cst.registerOutParameter(k, java.sql.Types.VARCHAR);
                        break;
                    case "java.lang.Integer":
                        cst.registerOutParameter(k, java.sql.Types.INTEGER);
                        break;
                    case "java.lang.Double":
                        cst.registerOutParameter(k, java.sql.Types.DOUBLE);
                        break;
                    case "float":
                        cst.registerOutParameter(k, java.sql.Types.FLOAT);
                    default:
                        cst.registerOutParameter(k, java.sql.Types.VARCHAR);
                        break;
                }
                k++;
            }
            
            cst.execute();
                        
            for(Object y:param_salida){
                switch(y.getClass().getName()){
                    case "java.lang.String":
                        resultado.add(cst.getString(j));
                        break;
                    case "java.lang.Integer":
                        resultado.add(cst.getInt(j));
                        break;
                    case "java.lang.Double":
                        resultado.add(cst.getDouble(j));
                        break;
                    case "float":
                        resultado.add(cst.getFloat(j));
                    default:
                        resultado.add(cst.getString(j));
                        break;
                } 
                j++;
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally{
            if(!cone.isClosed()) cone.close();
        }
        return resultado;
    }
    
    public boolean ejecutaQuery(String query) throws Exception{
        Connection cone = Conexion.getConexion();
        try {
            Statement st = cone.createStatement();
            st.execute(query);
            System.out.println("ejecución de query exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    public static List  ejecutarSentencia(String query){
        Connection cone = Conexion.getConexion();
        try {
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rmd = rs.getMetaData();
            ArrayList resu = new ArrayList();
            while(rs.next()){
                HashMap fila = new HashMap();
                resu.add(fila);
                
                for(int j=0; j < rmd.getColumnCount(); j++){
                    fila.put(rmd.getColumnName(j+1),rs.getObject(j+1));
                }
                
            }
        return resu;
        }catch (SQLException e){
            System.out.println("Error ..."+ e.getMessage());
            // DesHacerDatos();
            return null ;
//        }catch (IOException e){
//            System.out.println("Error ..."+ e.getMessage());
//            // DesHacerDatos();
//            return null ;
//        
            //DesconectarBaseDatos();
        }
    }
}
