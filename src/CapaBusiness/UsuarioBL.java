/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBusiness;

import CapaDatos.UsuarioDAO;
import CapaEntidades.UsuarioBE;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class UsuarioBL {
    public Integer Insertar(UsuarioBE usuarioBE){
    return 0;
    }
    public boolean Actualizar(UsuarioBE usuarioBE){
    return true;
    }
    public boolean Eliminar(UsuarioBE usuarioBE){
    return false;
    }
    public UsuarioBE GetUsuario(String Usuario, String Clave)throws SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();        
        return usuarioDao.GetVerificarUsuario(Usuario, Clave);
    }
    public ArrayList<UsuarioBE> GetUsuarioAll() throws SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();        
        return usuarioDao.GetAllClientes();
    }
}
