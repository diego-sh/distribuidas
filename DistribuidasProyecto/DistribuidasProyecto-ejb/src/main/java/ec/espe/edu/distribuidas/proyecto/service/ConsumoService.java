/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ConsumoActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.VisitaDAO;
import ec.espe.edu.distribuidas.proyecto.model.Cliente;
import ec.espe.edu.distribuidas.proyecto.model.Consumo;
import ec.espe.edu.distribuidas.proyecto.model.ConsumoActividad;
import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
import ec.espe.edu.distribuidas.proyecto.model.Visita;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author Diego
 */
public class ConsumoService {

    @EJB
    private ConsumoDAO consumoDAO;
    @EJB
    private ConsumoActividadDAO consumoActividadDAO;
    @EJB
    private VisitaDAO visitaDAO;

    public void crearVisita() {
        Establecimiento establecimientTMP = new Establecimiento();
        Visita visitaTMP = new Visita();
        Cliente clienteTMP = new Cliente();
        String codigoTransporte = null, cedula = null;
        String codigoUsuario = null;
        java.util.Date fechaVisita = new Date();
        visitaTMP.setCodigo("V0000001");
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

}
