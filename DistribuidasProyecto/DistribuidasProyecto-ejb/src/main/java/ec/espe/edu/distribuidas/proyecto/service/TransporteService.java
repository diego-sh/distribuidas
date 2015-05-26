/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.TransporteDAO;
import ec.espe.edu.distribuidas.proyecto.model.Transporte;
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
public class TransporteService {

    @EJB
    private TransporteDAO transporteDAO;

    public void crearTransporte(Transporte transporte) {
        Transporte transporteTMP = new Transporte();
        transporteTMP.setCodigo(transporte.getCodigo());
        List<Transporte> transportes = this.transporteDAO.find(transporteTMP);
        if (transportes == null) {
            this.transporteDAO.insert(transporte);
        } else {
            throw new RuntimeException("El Transporte: " + transporte.getCodigo() + "ya existe.");
        }
    }

    public void actualizarTransporte(Transporte transporte) {
        this.transporteDAO.update(transporte);
    }

    public void eliminarTransporte(String codigo) {
        Transporte transporteTMP = this.transporteDAO.findById(codigo, false);
        this.transporteDAO.remove(transporteTMP);
    }

    public Transporte obtenerTransportePorCodigo(String codigo) {
        return this.transporteDAO.findById(codigo, false);
    }

    public Transporte obtenerTransportePorPlaca(String placa) {
        Transporte transporteTMP = new Transporte();
        transporteTMP.setPlaca(placa);
        List<Transporte> transportes = this.transporteDAO.find(transporteTMP);
        if (transportes != null && !transportes.isEmpty()) {
            return transportes.get(0);
        } else {
            return null;
        }
    }
}
