/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.EstablecimientoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.UbicacionDAO;
import ec.espe.edu.distribuidas.proyecto.dao.VisitaDAO;
import ec.espe.edu.distribuidas.proyecto.model.Actividad;
import ec.espe.edu.distribuidas.proyecto.model.Consumo;
import ec.espe.edu.distribuidas.proyecto.model.ConsumoActividad;
import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
import ec.espe.edu.distribuidas.proyecto.model.Ubicacion;
import ec.espe.edu.distribuidas.proyecto.model.Visita;
import java.math.BigDecimal;
import java.util.Date;
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
public class ConsumoService {

    @EJB
    private ConsumoDAO consumoDAO;
    @EJB
    private ConsumoActividadDAO consumoActividadDAO;
    @EJB
    private VisitaDAO visitaDAO;
    @EJB
    private ActividadDAO actividadDAO;
    @EJB
    private EstablecimientoDAO establecimientoDAO;
    @EJB
    private UbicacionDAO ubicacionDAO;

    public void crearConsumo(Consumo consumo) {
        this.consumoDAO.insert(consumo);
    }

    public void crearConsumoAvtividad(ConsumoActividad consumoActividad) {
        this.consumoActividadDAO.insert(consumoActividad);
    }

    public void crearActividad(Actividad actividad) {
        Actividad actividadTMP = new Actividad();
        actividadTMP.setCodigo(actividad.getCodigo());
        List<Actividad> actividades = this.actividadDAO.find(actividadTMP);
        if (actividades == null) {
            this.actividadDAO.insert(actividad);
        } else {
            throw new RuntimeException("La Actividad: " + actividad.getCodigo() + "ya existe.");
        }
    }

    public void actualizarActividad(Actividad actividad) {
        this.actividadDAO.update(actividad);
    }

    public void eliminarActividad(String codigo) {
        Actividad actividadTMP = this.actividadDAO.findById(codigo, false);
        this.actividadDAO.remove(actividadTMP);
    }

    public Actividad obtenerActividadPorCodigo(String codigo) {
        return this.actividadDAO.findById(codigo, false);
    }

    public void crearVisita(Visita visita) {
        Date fechaVisita = new Date();
        
    }

    public BigDecimal valorVisita(String codigoStablecimiento) {
        BigDecimal valor = new BigDecimal("0.00");
        Establecimiento establecimientoTMP = new Establecimiento();
        establecimientoTMP = this.establecimientoDAO.findById(codigoStablecimiento, true);
        Ubicacion ubicacionTMP = new Ubicacion();
        ubicacionTMP = this.ubicacionDAO.findById(establecimientoTMP.getCodigo(), true);
        if (ubicacionTMP.getNumeroBloque() > 5) {
            return valor.add(new BigDecimal(20));
        } else {
            return valor.add(new BigDecimal(30));
        }
    }

}
