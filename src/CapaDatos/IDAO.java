/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import java.util.List;

/**
 *
 * @author francisco
 * @param <Entidad>
 */
public interface IDAO<Entidad> {
    
    public Entidad getEntidadId(int id) throws Exception;
    public void insertarEntidad(Entidad entidad) throws Exception;
    public void actualizarEntidad(Entidad entidad) throws Exception;
    public void eliminarEntidad(Entidad entidad) throws Exception;
    public List<Entidad> getListaEntidades(String query) throws Exception;
    
}
