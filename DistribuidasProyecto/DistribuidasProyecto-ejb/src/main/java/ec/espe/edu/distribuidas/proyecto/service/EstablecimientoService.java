/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import com.persist.common.dao.Order;
import ec.espe.edu.distribuidas.proyecto.dao.EstablecimientoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.UbicacionDAO;
import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
import ec.espe.edu.distribuidas.proyecto.model.Ubicacion;
import java.util.List;
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

    public void crearUbicacion(Ubicacion ubicacion) {
        
       Ubicacion ubicacionTMP = this.ubicacionDAO.findById(ubicacion.getCodigo(), false);
        if (ubicacionTMP == null) {
            this.ubicacionDAO.insert(ubicacion);
        } else {
            throw new RuntimeException("La Ubicacion: " + ubicacion.getCodigo() + "ya existe.");
        }
    }

    public void actualizarUbicacion(Ubicacion ubicacion) {
        this.ubicacionDAO.update(ubicacion);
    }

    public void eliminarUbicacion(String codigo) {
        Ubicacion ubicacionTMP = this.ubicacionDAO.findById(codigo, false);
        this.ubicacionDAO.remove(ubicacionTMP);
    }

    public Ubicacion obtenerUbicacionPorCodigo(String codigo) {
        return this.ubicacionDAO.findById(codigo, false);
    }
    
    public List<Ubicacion> obtenerAllUbicacion(){
        List<Ubicacion> ubicaciones= null;       
        ubicaciones=this.ubicacionDAO.findAll();
        if(ubicaciones==null){
            throw new RuntimeException("El Ubicaciones 0");
        }
        
        return ubicaciones;
    }

    public void crearEstablecimiento(Establecimiento establecimiento) {
        Establecimiento establecimientoTMP = this.establecimientoDAO.findById(establecimiento.getCodigo(), false);
        if (establecimientoTMP == null) {
            this.establecimientoDAO.insert(establecimiento);
        } else {
            throw new RuntimeException("El Establecimiento: " + establecimiento.getCodigo() + "ya existe.");
        }
    }

    public void actualizarEstablecimiento(Establecimiento establecimiento) {
        this.establecimientoDAO.update(establecimiento);
    }

    public void eliminarEstablecimiento(String codigo) {
        Establecimiento establecimientoTMP = this.establecimientoDAO.findById(codigo, false);
        this.establecimientoDAO.remove(establecimientoTMP);
    }

    public Establecimiento ObtenerEstablecimientoPoCodigo(String codigo) {
        return this.establecimientoDAO.findById(codigo, false);
    }

    public List<Establecimiento> ObtenerEstablecimientoPorUbicacion(String codigoUbicacion) {
        Establecimiento establecimientoTMP = new Establecimiento();
        establecimientoTMP.setCodigoUbicacion(codigoUbicacion);
        return this.establecimientoDAO.findO(establecimientoTMP, Order.ascendente("Nombre_Establecimiento"));
    }

    public List<Establecimiento> obtenerAllEstablecimiento() {
        return this.establecimientoDAO.findAll();
    }

}
