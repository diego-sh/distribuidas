/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.EstablecimientoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.UbicacionDAO;
import ec.espe.edu.distribuidas.proyecto.model.Ubicacion;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Diego
 */
@LocalBean
@Stateless
public class EstablecimientoService {

    @EJB
    private EstablecimientoDAO establecimientoDAO;
    @EJB
    private UbicacionDAO ubicacionDAO;
    
    public void crearUbicacion(Ubicacion ubicacion){
        Ubicacion ubicacionTMP= new Ubicacion();
        
    
    }
    
    

}
