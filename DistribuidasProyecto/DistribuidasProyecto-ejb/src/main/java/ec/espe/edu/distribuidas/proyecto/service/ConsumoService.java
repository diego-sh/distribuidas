/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.VisitaDAO;
import ec.espe.edu.distribuidas.proyecto.model.Actividad;
import ec.espe.edu.distribuidas.proyecto.model.Consumo;
import ec.espe.edu.distribuidas.proyecto.model.ConsumoActividad;
import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
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

    public void crearVisita() {
        Establecimiento establecimientTMP = new Establecimiento();
        Visita visitaTMP = new Visita();        
        String codigoTransporte = null, cedula = null;
        String codigoUsuario = null;
        Date fechaVisita = new Date();
        visitaTMP.setCodidoEstablecimiento(establecimientTMP.getCodigo());
        visitaTMP.setCedula(cedula);
        visitaTMP.setCodigoTransporte(codigoTransporte);
        visitaTMP.setCodigoUsuario(codigoUsuario);
        visitaTMP.setFecha(fechaVisita);
        visitaTMP.setValor(BigDecimal.ZERO);
        visitaTMP.setEstadoFactura(false);
        this.visitaDAO.insert(visitaTMP);
    }

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

}
