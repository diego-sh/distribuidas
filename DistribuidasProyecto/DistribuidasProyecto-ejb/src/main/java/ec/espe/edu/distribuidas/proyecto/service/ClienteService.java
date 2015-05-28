/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ClienteDAO;
import ec.espe.edu.distribuidas.proyecto.model.Cliente;
import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Diego
 */
public class ClienteService {

    @EJB
    private ClienteDAO clienteDAO;

    public void crearCliente(Cliente cliente) {
        Cliente clienteTMP = new Cliente();
        clienteTMP.setCedula(cliente.getCedula());
        List<Cliente> clientes = this.clienteDAO.find(clienteTMP);
        if (clientes == null) {
            this.clienteDAO.insert(cliente);
        } else {
            throw new RuntimeException("El Cliente: " + cliente.getCedula() + "ya existe.");
        }
    }

    public void actualizarCliente(Cliente cliente) {
        this.clienteDAO.update(cliente);
    }

    public void eliminarCliente(String codigo) {
        Cliente clienteTMP = this.clienteDAO.findById(codigo, false);
        this.clienteDAO.remove(clienteTMP);
    }

    public Cliente ObtenerClientePorCodigo(String codigo) {
        return this.clienteDAO.findById(codigo, false);
    }
}
