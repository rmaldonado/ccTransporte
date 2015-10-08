/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBusiness;

import CapaDatos.EmpleadoDAO;
import CapaEntidades.EmpleadoBE;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class EmpleadoBL {
        public Integer Insertar(EmpleadoBE empleadoBE){
    return 0;
    }
    public boolean Actualizar(EmpleadoBE empleadoBE){
    return true;
    }
    public boolean Eliminar(EmpleadoBE empleadoBE){
    return false;
    }
    public EmpleadoBE GetEmpleadoById(int id)throws SQLException {
        EmpleadoDAO empleadoDao = new EmpleadoDAO();        
        return empleadoDao.GetEmpleadoByID(id);
    }
//    public ArrayList<EmpleadoBE> GetUsuarioAll() throws SQLException {
//        EmpleadoDAO usuarioDao = new EmpleadoDAO();        
//        return usuarioDao.GetAllClientes();
//    }
    
}
