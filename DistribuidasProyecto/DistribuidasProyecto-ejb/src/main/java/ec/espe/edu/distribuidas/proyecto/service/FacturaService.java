/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoActividadDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ConsumoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.DetalleDAO;
import ec.espe.edu.distribuidas.proyecto.dao.FacturaDAO;
import ec.espe.edu.distribuidas.proyecto.dao.VisitaDAO;
import ec.espe.edu.distribuidas.proyecto.model.Actividad;
import ec.espe.edu.distribuidas.proyecto.model.Consumo;
import ec.espe.edu.distribuidas.proyecto.model.ConsumoActividad;
import ec.espe.edu.distribuidas.proyecto.model.ConsumoActividadPK;
import ec.espe.edu.distribuidas.proyecto.model.Detalle;
import ec.espe.edu.distribuidas.proyecto.model.Factura;
import ec.espe.edu.distribuidas.proyecto.model.Visita;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class FacturaService {

    @EJB
    private FacturaDAO facturaDAO;
    @EJB
    private ConsumoActividadDAO consumoActividadDAO;
    @EJB
    private VisitaDAO visitaDAO;
    @EJB
    private ConsumoDAO consumoDAO;
    @EJB
    private DetalleDAO detalleDAO;
    @EJB
    private ActividadDAO actividadDAO;

    public void crearFactura(Factura factura) {
        Date fecha = new Date();
        factura.setFecha(fecha);
        this.facturaDAO.insert(factura);
        crearDetalle(factura);
        calcularTotalFactura(factura);
        actualizarEstadoFacturaVisita(factura.getCedula());
    }

    public List<ConsumoActividad> obtenerAllConsumoActividadPorCliente(String cedula) {
        ConsumoActividadPK conSumPKTEMP;
        List<ConsumoActividad> consumosActividades = new ArrayList<ConsumoActividad>();
        List<Visita> visitaTMP = obtenerVisitasPorCliente(cedula);
        if (visitaTMP != null) {
            for (int i = 0; i < visitaTMP.size(); i++) {
                conSumPKTEMP = new ConsumoActividadPK();
                conSumPKTEMP.setCodigoVisita(visitaTMP.get(i).getCodigo());
                List<ConsumoActividad> conActividades = obtenerConsumoActividadPorCodVisita(conSumPKTEMP);
                if (conActividades != null) {
                    consumosActividades.addAll(conActividades);
                } else {
                    throw new RuntimeException("No Existen ConsumosActiviades..");
                }
            }
            return consumosActividades;

        } else {
            return null;
        }

    }

    public List<Visita> obtenerVisitasPorCliente(String cedula) {
        Visita visitaTMP = new Visita();
        visitaTMP.setCedula(cedula);
        visitaTMP.setEstadoFactura(false);
        List<Visita> visitas = this.visitaDAO.find(visitaTMP);
        if (visitas != null && !visitas.isEmpty()) {
            return visitas;
        } else {
            return null;
        }

    }

    public List<ConsumoActividad> obtenerConsumoActividadPorCodVisita(ConsumoActividadPK conSumPK) {
        ConsumoActividad consumoActividadTMP = new ConsumoActividad();
        consumoActividadTMP.setPk(conSumPK);
        List<ConsumoActividad> consumos = this.consumoActividadDAO.find(consumoActividadTMP);
        if (consumos != null & !consumos.isEmpty()) {
            return consumos;
        } else {
            return null;
        }

    }

    public List<Consumo> obtenerAllConsumosPorCliente(String cedula) {
        Consumo consumoTMP;
        List<Consumo> Consumos = new ArrayList<Consumo>();
        List<Visita> visitaTMP = obtenerVisitasPorCliente(cedula);
        if (visitaTMP != null) {
            for (int i = 0; i < visitaTMP.size(); i++) {
                consumoTMP = new Consumo();
                consumoTMP.setCodigoVisita(visitaTMP.get(i).getCodigo());
                List<Consumo> consumosAux = this.consumoDAO.find(consumoTMP);
                if (consumosAux != null && !consumosAux.isEmpty()) {
                    Consumos.addAll(consumosAux);
                } else {
                    throw new RuntimeException("No Existen Consumos..");
                }
            }
            return Consumos;

        } else {
            return null;
        }
    }

    public void crearDetalle(Factura factura) {
        Detalle detalleTMP;
        Actividad actividadTMP;
        BigDecimal total;
        List<Consumo> consumos = obtenerAllConsumosPorCliente(factura.getCedula());
        List<ConsumoActividad> consumosActidad = obtenerAllConsumoActividadPorCliente(factura.getCedula());
        if (consumos != null) {
            for (int i = 0; i < consumos.size(); i++) {
                detalleTMP = new Detalle();
                detalleTMP.setCodigoFactura(factura.getCodigo());
                detalleTMP.setCantidad(consumos.get(i).getCantidad());
                detalleTMP.setDescripcion(consumos.get(i).getProducto().getNombre());
                detalleTMP.setPrecioUnitario(consumos.get(i).getProducto().getPrecio());
                BigDecimal cantidad = new BigDecimal(detalleTMP.getCantidad());
                total = cantidad.multiply(detalleTMP.getPrecioUnitario());
                detalleTMP.setTotal(total);
                this.detalleDAO.insert(detalleTMP);
            }
        }
        if (consumosActidad != null) {
            for (int i = 0; i < consumosActidad.size(); i++) {
                detalleTMP = new Detalle();
                detalleTMP.setCodigoFactura(factura.getCodigo());
                detalleTMP.setCantidad(1);
                actividadTMP = obtenerActividadPorCodigo(consumosActidad.get(i).getPk().getCodigoActividad());
                detalleTMP.setDescripcion(actividadTMP.getNombre());
                detalleTMP.setPrecioUnitario(consumosActidad.get(i).getValor());
                BigDecimal cantidad = new BigDecimal(detalleTMP.getCantidad());
                total = cantidad.multiply(detalleTMP.getPrecioUnitario());
                detalleTMP.setTotal(total);
                this.detalleDAO.insert(detalleTMP);
            }
        }
    }

    public Actividad obtenerActividadPorCodigo(String codigo) {
        return this.actividadDAO.findById(codigo, false);
    }

    public List<Detalle> obtenerDetallesPorCodFactura(Integer codigo) {
        Detalle detalleTMP = new Detalle();
        detalleTMP.setCodigoFactura(codigo);
        return this.detalleDAO.find(detalleTMP);
    }

    public void calcularTotalFactura(Factura factura) {
        BigDecimal totalPago = null;
        BigDecimal sumaItems = new BigDecimal("0.01");
        List<Detalle> detalles = obtenerDetallesPorCodFactura(factura.getCodigo());
        if (detalles != null && !detalles.isEmpty()) {
            for (int i = 0; i < detalles.size(); i++) {
                totalPago = sumaItems.add(detalles.get(i).getTotal());
            }
            factura.setTotal(totalPago);
            this.facturaDAO.update(factura);
        }
    }

    public void actualizarEstadoFacturaVisita(String cedula) {
        List<Visita> visitas = obtenerVisitasPorCliente(cedula);
        for (int i = 0; i < visitas.size(); i++) {
            visitas.get(i).setEstadoFactura(true);
        }
    }
}
